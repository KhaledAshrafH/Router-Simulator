# Router-Simulator with Java Threading and Semaphore

This project simulates a limited number of devices connected to a router's Wi-Fi using Java threading and semaphores. The router is designed to limit the number of open connections, allowing only a fixed number of devices to connect simultaneously. When the maximum number of connections is reached, incoming devices must wait until an existing connection is released.

## Overview

The Router Simulation project demonstrates how semaphores can be used to limit the number of concurrent connections in a router's Wi-Fi system. It provides a simplified model of a router and devices connecting to it. The simulation is implemented in Java and utilizes threading and semaphores for synchronization.

The project consists of the following classes:

1. **Router**: Manages the router's connections and provides methods to occupy and release connections.

2. **Semaphore**: Implements a semaphore, a synchronization primitive used to control access to a shared resource. The router uses the semaphore to limit the number of concurrent connections.

3. **Device**: Represents a device that can connect to the router. Each device is implemented as a separate thread and performs connection, online activity, and disconnection actions.

4. **Network**: Contains the main method of the program. It prompts the user for input, creates device threads, and starts the simulation.

## Installation

To run the Router Simulation, follow these steps:

1. Clone the GitHub repository:
```
git clone https://github.com/KhaledAshrafH/Router-Simulator
```

2. Open the project in your preferred Java development environment.

3. Build the project to compile the source code.

4. Run the project, and the simulation will start.

## Usage

Upon running the Router Simulation, you will be prompted to provide the following information:

1. Enter the maximum number of Wi-Fi connections the router can accept.

2. Enter the total number of devices that wish to connect.

3. For each device, enter the device name and type.

After providing the necessary input, the simulation will start, and the output will be displayed in the console.

## Simulation

The simulation follows the rules outlined below:

- The Wi-Fi connections are initially empty.

- If a client is logged in and can be served (i.e., a connection is available), the client performs the following activities: connect, perform online activity, and log out.

- If a client arrives and all connections are occupied, the client must wait until a connection becomes available.

- After a client finishes their service, they leave, and one of the waiting clients (if any) connects to the internet.

The simulation output consists of messages that indicate the arrival of devices, connection status, log in/out activities, and online activities.

## Sample Output

**Sample Input:**
```
What is the number of Wi-Fi Connections?
2
What is the number of devices Clients want to connect?
4
Device 1 Name: Khaled
Device 1 Type: Mobile
Device 2 Name: Mahmoud
Device 2 Type: Tablet
Device 3 Name: Passant
Device 3 Type: PC
Device 4 Name: Salma
Device 4 Type: Laptop
```

**Sample Output:**
```
Khaled (Mobile) arrived
Mahmoud (Tablet) arrived
Connection 1: Khaled Occupied
Connection 2: Mahmoud Occupied
Salma (Laptop) arrived and waiting
Passant (PC) arrived and waiting
Connection 1: Khaled login
Connection 1: Khaled performs online activity
Connection 2: Mahmoud login
Connection 2: Mahmoud performs online activity
Connection 1: Khaled Logged out
Connection 1: Salma Occupied
Connection 1: Salma log in
Connection 1: Salma performs online activity
Connection 2: Mahmoud Logged out
Connection 2: Passant Occupied
```

## Contributing

Contributions to the Router Simulation project are welcome. If you have any ideas, improvements, or bug fixes, please submit a pull request. For major changes, please open an issue first to discuss the proposed changes.


## Conclusion

The Router Simulation with Java Threading and Semaphore project provides a practical implementation of a router simulation using Java threading and semaphores. By simulating the behavior of devices connecting, performing online activities, and disconnecting, the project demonstrates how to control the number of concurrent connections and manage resource allocation in a multithreaded environment.

The project's modular structure and clear separation of responsibilities make it easy to understandand extend. The use of semaphores ensures that the maximum number of connections is not exceeded, allowing devices to wait until a connection becomes available.

The included GUI enhances the user experience by providing a user-friendly interface for input and displaying the simulation output. The simulation output provides valuable insights into the order of execution and the activities performed by each device.


## License

The Router Simulation project is licensed under the [MIT License](LICENSE.md).
