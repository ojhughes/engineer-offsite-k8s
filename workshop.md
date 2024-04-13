---
title: Kubernetes for the Busy Neo4j Developer
tags: Templates, Talk
description: View the slide with "Slide Mode".
---

# Kubernetes for the Busy Neo4j Developer!

<!-- Put the link to this slide here so people can follow -->
slide: https://hackmd.io/p/template-Talk-slide

GitHub: https://github.com/ojhughes/engineer-offsite-k8s

---

This is a collaborative, hands on workshop - you can use your own laptop or work with a buddy

---
## What we wll do

- Learn some basic Kubernetes (aka K8s) in a collaborative way
- Set up prerequesite tools
- Deploy a simple Java application to K8s
    - Build a Docker image for the app
    - Push the app to a Docker repo
    - Create deployment and service descriptors for Kubernetes
    - Deploy and run the app on Kubernetes
---

## Prerequesites

- A laptop to follow along with the examples. Ideally Linux, Mac or Windows with [ WSL installed ](https://learn.microsoft.com/en-us/windows/wsl/install). You can pair up with someone if you don't have a laptop.
- Basic knowledge of Maven and Linux command line (but don't worry we can help if you get stuch)

---

## Required Tools

We are going to need to install some tools on our machine to run through the workshop. 

:::info
:question: **Please ask for help if you have trouble installing any of the tools**
:::

- [Java JDK 21 ](https://adoptium.net/en-GB/temurin/releases/)(**Not JRE**)
    - The example can probably be downgraded to an earlier version if you want
    - You can install the JDK using from
        - [The Temurin relase page](https://adoptium.net/en-GB/temurin/releases/)
        - Using Homebrew for Mac users 

            `brew tap homebrew/cask-versions`
            
            `brew install --cask temurin`
            
        - Using the [SDKMan](https://sdkman.io/install) tool
        - Using a Linux package manager
- [git](https://github.com/git-guides/install-git)
- [kubectl](https://kubernetes.io/docs/tasks/tools/)
- [minikube](https://kubernetes.io/docs/tasks/tools/)
- [IntelliJ](https://www.jetbrains.com/help/idea/installation-guide.html)
    - VSCode will also work
- Enable the [Kubernetes plugin](https://www.jetbrains.com/help/idea/kubernetes.html) for IntelliJ **or** [K8s extension for VSCode](https://code.visualstudio.com/docs/azure/kubernetes)

---
## Setting up Minikube 

Minikube is a popular tool that allows developers to run Kubernetes locally. It simplifies the process of learning and experimenting with Kubernetes by enabling a single-node Kubernetes cluster.

:::success
:information_source: If you have trouble setting up Minikube on your machine, I have a Google Cloud cluster that can be shared for the workshop.
:::

### Setting up Docker Desktop / Podman for Minikube
:::warning
This step doesn't apply to Linux users as you can install Docker natively
:::

If you have an M1 / M2 Mac, you will need to either install Docker Desktop or Podman to run Minikube. If you have an Intel based Mac you can either use Docker or the Hyperkit Driver to run Minikube (I haven't tested this!)

#### Option 1: Installing Docker Desktop
Note Docker Desktop is **Paid Software** but free for personal use
https://docs.docker.com/desktop/install/mac-install/

#### Option 2: Installing Podman
https://podman.io/docs/installation

### Starting Minikube
Assuming you have already installed Minikube in the previous step, start it up using the command;
```bash
minikube start
```
### Verifying the Installation

After starting Minikube, verify that it's running correctly:

```bash
minikube status
```
This should show the following output;

```bash
minikube
type: Control Plane
host: Running
kubelet: Running
apiserver: Running
kubeconfig: Configured
```

Next verify that the `kubectl` CLI is installed and configured correctly using the command;
```bash
kubectl get nodes
```
The output should look like this;

```bash
NAME       STATUS   ROLES           AGE    VERSION
minikube   Ready    control-plane   2d3h   v1.28.3
```

### (Optional) Using the Minikube dashboard

Minikube includes a built-in dashboard for K8s, which provides a web-based user interface to interact with your cluster:

```bash
minikube dashboard
```
![Screenshot 2024-04-13 at 18.52.04](https://hackmd.io/_uploads/B1SUUBueR.png)


## Cloning and exploring the sample application

A simple "Hello World" Java API was created for the workshop. It is built using Dropwizard, which is a simple and lightweight Java web framework.

Grab the sample application by cloning it from GitHub

```bash
git clone git@github.com:ojhughes/engineer-offsite-k8s.git
cd engineer-offsite-k8s

```
Now verify that you can build the app using Maven;

```bash
./mvnw package
```


---
## Containerising the Java Application Using Jib

---