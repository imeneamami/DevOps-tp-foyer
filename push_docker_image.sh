#!/bin/bash

# Variables
DOCKER_USERNAME="imen1amami"
DOCKER_PASSWORD="donghua&&&12"
IMAGE_NAME="imen1amami/tp-foyer:5.0.0"

# Login to Docker Hub
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin

# Push the image
docker push $IMAGE_NAME






