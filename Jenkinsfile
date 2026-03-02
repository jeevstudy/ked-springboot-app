pipeline {
    agent any
    environment {
        EXTERNAL_REGISTRY = "192.168.1.60:5005"
        HOST_VM_IP = "192.168.1.60"

        INTERNAL_REGISTRY = "k3d-jeevregistry.localhost:5005"
        IMAGE_NAME = "spring-boot-app"
        TAG = "v${BUILD_NUMBER}"

    }
    stages {
        stage('Maven Build') {
            steps {
                sh """
                docker run --rm \
                --user \$(id -u):\$(id -g) \
                -v \$(pwd):/app \
                -v /tmp/.m2:/var/maven/.m2 \
                -e MAVEN_CONFIG=/var/maven/.m2 \
                -w /app \
                maven:3.9-eclipse-temurin-17 \
                mvn clean package -DskipTests
                """
            }
        }
        stage('Build & Push Image') {
            steps {
                sh "docker build -t ${EXTERNAL_REGISTRY}/${IMAGE_NAME}:${TAG} ."
                sh "docker push ${EXTERNAL_REGISTRY}/${IMAGE_NAME}:${TAG}"
            }
        }
        stage('Deploy to K3d') {
            steps {
                sh """
                cat <<EOF | kubectl apply -f -
apiVersion: apps/v1
kind: Deployment
metadata:
  name: spring-app
spec:
  replicas: 1
  selector:
    matchLabels:
      app: spring-app
  template:
    metadata:
      labels:
        app: spring-app
    spec:
      hostNetwork: true               # <--- MUST be here (under spec)
      dnsPolicy: ClusterFirstWithHostNet # <--- Required when using hostNetwork
      containers:
      - name: spring-boot
        image: ${INTERNAL_REGISTRY}/${IMAGE_NAME}:${TAG}
        imagePullPolicy: Always
        env:
        - name: MONGO_USER
          valueFrom: { secretKeyRef: { name: mongo-creds, key: username } }
        - name: MONGO_PASS
          valueFrom: { secretKeyRef: { name: mongo-creds, key: password } }
        - name: MONGODB_URI
          value: "mongodb://\$(MONGO_USER):\$(MONGO_PASS)@${HOST_VM_IP}:27017/learningDB?authSource=learningDB"
        ports:
        - containerPort: 8081
---
apiVersion: v1
kind: Service
metadata:
  name: spring-boot-svc
spec:
  type: NodePort
  selector:
    app: spring-app
  ports:
    - port: 8081
      targetPort: 8081
      nodePort: 30081
EOF
                """
            }
        }
    }
}