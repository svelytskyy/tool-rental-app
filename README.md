## Features

- Tool checkout process with rental charge calculation
- Management of tool and tool cost information
- Caching mechanism for tool data to optimize performance
- Command-line interface for user interaction

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

### Installation

1. **Clone the repository:**

    ```sh
    git clone https://github.com/svelytskyy/tool-rental-app.git
    cd tool-rental-app
    ```

2. **Build the application:**

    ```sh
    mvn clean package
    ```

### Running the Application

To run the application with interactive mode enabled, use the following command:

```sh
mvn spring-boot:run -Dspring-boot.run.arguments="--runInteractive=true"
