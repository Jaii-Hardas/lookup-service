# Variables
SERVICES := lookup-service
REPO := https://github.com/Jaii-Hardas/lookup-service.git
DOCKER_NAMESPACE := jaii1515

# Main target 
default: clone status pull stash build_lib build_services build_images push_images

# Clone all services and the JavaFX library project
clone:
	@echo "Starting to clone services and the JavaFX library project..."
	@for service in $(SERVICES); do \
		git clone $(REPO) $$service; \
	done
	@echo "Cloning the library project..."
	@git clone $(REPO) $(LIBRARY)

# Display the Git status for all projects
status:
	@echo "Checking Git status for each project..."
	@for project in $(SERVICES) $(LIBRARY); do \
		echo "Status for $$project:"; \
		(cd $$project && git status); \
	done

# Pull the latest changes for all projects
pull:
	@echo "Fetching the latest changes from remote repositories..."
	@for project in $(SERVICES) $(LIBRARY); do \
		echo "Pulling updates for $$project..."; \
		(cd $$project && git pull); \
	done

# Stash any local changes for all projects
stash:
	@echo "Stashing local changes in all projects..."
	@for project in $(SERVICES) $(LIBRARY); do \
		echo "Stashing changes for $$project..."; \
		(cd $$project && git stash); \
	done

# Build the JavaFX library project
build_lib:
	@echo "Compiling the JavaFX library project..."
	@(cd $(LIBRARY) && mvn clean install)

# Build all services
build_services:
	@echo "Compiling all service projects..."
	@for service in $(SERVICES); do \
		echo "Building $$service..."; \
		(cd $$service && mvn clean install); \
	done

# Create Docker images for each service
build_images:
	@echo "Creating Docker images for all services..."
	@for service in $(SERVICES); do \
		echo "Building Docker image for $$service..."; \
		(cd $$service && docker build -t $(DOCKER_NAMESPACE)/$$service:latest .); \
	done

# Push Docker images to registry
push_images:
	@echo "Pushing Docker images to registry..."
	@for service in $(SERVICES); do \
		echo "Pushing Docker image for $$service..."; \
		docker push $(DOCKER_NAMESPACE)/$$service:latest; \
	done
