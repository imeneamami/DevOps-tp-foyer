#!/bin/bash

# Use environment variables for Docker Hub credentials
DOCKER_USERNAME="${DOCKER_USERNAME_ENV}"
DOCKER_PASSWORD="${DOCKER_PASSWORD_ENV}"
IMAGE_NAME="imen1amami/tp-foyer:5.0.0"

# Login to Docker Hub
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin

# Push the image
docker push $IMAGE_NAME