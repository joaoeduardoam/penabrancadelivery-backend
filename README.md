

<h1 align="center">
    <a href="https://laravelcollective.com/tools/banner">
        <img alt="Banner" title="#Pena Branca Delivery - Backend" style="object-fit: cover; height:250px;" src="https://i.pinimg.com/originals/73/00/d3/7300d3dc8d1cb1f75229adc41d135a1f.jpg"  />
    </a>
</h1>

# 📝 Aplicação backend do ecommerce Pena Branca Delivery

<p align="center"> Aplicação Backend para E-commerce de Carnes Assadas

Este projeto é uma aplicação backend desenvolvida para um e-commerce especializado em carnes assadas. O foco principal é a venda de frango assado, churrascos de carne bovina (picanha, alcatra, entre outros), suína (pernil, costela), e espetos mistos (contendo toscana, coxa de frango e pernil de leitão). A aplicação oferece uma estrutura para a gestão de produtos, pedidos e clientes, proporcionando uma experiência prática e eficiente tanto para os clientes quanto para a administração da loja. </p>

<h2 align="center">
  <img src="https://img.shields.io/badge/web%3F-ok-blue?style=for-the-badge" alt="Sistema web Ok" />
  <img src="https://img.shields.io/badge/server%3F-ok-blue?style=for-the-badge" alt="Server Ok" />
  <img src="https://img.shields.io/badge/Mobile-OK-blue?style=for-the-badge" alt="Aplicativo mobile Ok" />
</h2>

## ⚙ Instalação   

Com arquivo de manisfestos Kubernetes você pode realizar o deploy no seu cluster Kubernetes.
Copie o código abaixo faça as alterações das variáveis no config-map e no secret.    

-------------
### Comandos para realizar o deploy

| Tecnologia | Comando para instalar |
|------------|-----------------------|
|Kubenetes| ``` kubectl apply -f k8s-config.yml ``` |

### YAML dos manifestos Kubernetes para deploy da aplicação(arquivo está na raiz do projeto):

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: secret
type: Opaque
data:
  mysql-password: xxxxxxxxxxxxxxxxxxx # password must be in base64
  mail-password: xxxxxxxxxxxxxxxxxxxx
  client-secret: xxxxxxxxxxxxxxxxxxxx

---

apiVersion: v1
kind: ConfigMap
metadata:
  name: config-map
data:
  spring-datasource-url: "jdbc:mysql://mysql/penabranca?createDatabaseIfNotExist=true"
  mail-user: xxxxxxxxx@xxxx.com
  client-id: Client_Id_xxxxxxxxxxxxxxxxxxxxxxxx

---


apiVersion: storage.k8s.io/v1
kind: StorageClass
metadata:
  name: local-storage
provisioner: kubernetes.io/no-provisioner
reclaimPolicy: Retain # default value is Delete
volumeBindingMode: WaitForFirstConsumer

---

apiVersion: v1
kind: PersistentVolume
metadata:
  name: mysql-pv
  labels:
    type: local
spec:
  storageClassName: local-storage
  capacity:
    storage: 256Mi
  accessModes:
    - ReadWriteOnce
  persistentVolumeReclaimPolicy: Retain
  local:
    path: "/db-penabranca" # Directory must be created in the cluster k8s node ... name node must be setted in the field below
  nodeAffinity:
    required:
      nodeSelectorTerms:
        - matchExpressions:
            - key: kubernetes.io/hostname
              operator: In
              values:
                - node-clusterk8s-name  # name of the node in the cluster k8s

---

apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: mysql-pvc
spec:
  storageClassName: local-storage
  volumeName: mysql-pv
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 256Mi


---


apiVersion: v1
kind: Service
metadata:
  name: mysql
spec:
  selector:
    app: mysql
  ports:
    - port: 3306

---

apiVersion: apps/v1
kind: Deployment
metadata:
  name: mysql-deploy
spec:
  selector:
    matchLabels:
      app: mysql
  template:
    metadata:
      labels:
        app: mysql
    spec:
      containers:
        - name: mysql-container
          image: mysql
          ports:
            - containerPort: 3306
          env:
            # Define as variáveis de ambiente
            - name: MYSQL_ROOT_PASSWORD # Note que aqui a variável está definida em caixa alta, diferente da chave no ConfigMap.
              valueFrom:
                secretKeyRef:
                  name: secret         # Nome do Secret de onde esse valor vem.
                  key: mysql-password

          volumeMounts:
            - name: storage
              mountPath: /var/lib/mysql
      volumes:
        - name: storage
          persistentVolumeClaim:
            claimName: mysql-pvc

---

apiVersion: apps/v1
kind: Deployment
metadata:
  name: penabrancadelivery-backend
spec:
  replicas: 1
  selector:
    matchLabels:
      app: penabrancadelivery-backend
  template:
    metadata:
      labels:
        app: penabrancadelivery-backend
    spec:
      containers:
        - name: container-penabrancadelivery-backend
          image: joaoeduardoam/penabrancadelivery-backend:1.0
          ports:
            - containerPort: 8080
          env:
            # Define as variáveis de ambiente
            - name: SPRING_DATASOURCE_URL # Note que aqui a variável está definida em caixa alta,
              valueFrom:
                configMapKeyRef:
                  name:   config-map         # O ConfigMap de onde esse valor vem.
                  key: spring-datasource-url
            - name: MAIL_USER # Note que aqui a variável está definida em caixa alta, diferente da chave no ConfigMap.
              valueFrom:
                configMapKeyRef:
                  name: config-map         # O ConfigMap de onde esse valor vem.
                  key: mail-user
            - name: MAIL_PASSWORD # Note que aqui a variável está definida em caixa alta, diferente da chave no ConfigMap.
              valueFrom:
                secretKeyRef:
                  name: secret         # Nome do Secret de onde esse valor vem.
                  key: mail-password
            - name: CLIENT_ID # Note que aqui a variável está definida em caixa alta, diferente da chave no ConfigMap.
              valueFrom:
                configMapKeyRef:
                  name: config-map         # O ConfigMap de onde esse valor vem.
                  key: client-id
            - name: CLIENT_SECRET # Note que aqui a variável está definida em caixa alta, diferente da chave no ConfigMap.
              valueFrom:
                secretKeyRef:
                  name: secret         # Nome do Secret de onde esse valor vem.
                  key: client-secret

---

apiVersion: v1
kind: Service
metadata:
  name: penabrancadelivery-backend-service
spec:
  selector:
    app: penabrancadelivery-backend
  ports:
    - port: 80
      targetPort: 8080
      nodePort: 30001
  type: NodePort
```



Made with ❤️ by Joao Eduardo AM

Gostou? Deixe uma estrelinha para ajudar o projeto ⭐

- [Voltar ao Início](#index)
