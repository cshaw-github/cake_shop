# Cake Shop

This project is a demonstration of a clean architecture approach for an Android application, utilizing the MVVM pattern, repository pattern, Koin for dependency injection, Jetpack Compose for the UI, and MockK for unit testing.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Architecture](#architecture)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)

## Introduction

The Cake Shop project showcases a modern Android application built with a focus on clean architecture principles. The application follows the MVVM (Model-View-ViewModel) pattern and utilizes the repository pattern for data management. Dependency injection is handled by Koin, and the UI is crafted using Jetpack Compose. Unit tests are written using MockK to ensure code reliability and maintainability.

## Features

- Clean architecture with clear separation of concerns
- MVVM pattern for better state management
- Repository pattern for data handling
- Dependency injection with Koin
- Jetpack Compose for declarative UI development
- Comprehensive unit tests with MockK
- Fetching data from server using Ktor

## Architecture

The project follows the Clean Architecture principles, ensuring a scalable, maintainable, and testable codebase. The architecture is divided into several layers:

1. **Presentation Layer**: Contains UI components built with Jetpack Compose and ViewModels that handle the presentation logic.
2. **Domain Layer**: Includes use cases which encapsulate the business logic of the application.
3. **Data Layer**: Manages data from various sources (e.g., network) using the repository pattern.
4. **Data Source Layer**: Interfaces with external data sources, such as APIs. This layer is responsible for data retrieval. Ktor is used to fetch cake data from a server.
5. **Dependency Injection Layer**: Uses Koin for providing dependencies throughout the application.

## Technologies Used

- **Kotlin**: Primary programming language
- **Jetpack Compose**: For building the UI
- **Koin**: For dependency injection
- **MockK**: For unit testing
- **Ktor**: For HTTP networking
- **Coroutines**: For asynchronous programming
- **StateFlow**: For observing data changes in the UI

## Setup and Installation

To set up the project locally, follow these steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/cshaw-github/cake_shop.git
