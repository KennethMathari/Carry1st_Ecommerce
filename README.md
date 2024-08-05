 # Carry1st Ecommerce App
This is a prototype for an eCommerce platform built as a native Android application using Jetpack Compose and Kotlin. The application showcases a product listing and a detailed product info page, including basic cart functionality.

## Setup
To set up and run this project on your local machine, follow these steps:

1. Clone the repository:
> git clone <https://github.com/KennethMathari/Carry1st_Ecommerce>
2. Open the project in Android Studio.
- Launch Android Studio and select Open an existing Android Studio project. Then navigate to the directory where you cloned the repository and select the project.
3. Sync Gradle files:
- After opening the project, Gradle will automatically start syncing. Wait for the process to finish.

## Build and Run
After opening the project, build and run to install the app in the emulator or a connected device.

## App Features
1. <b>Product Listing Page</b> 
 - Displays a list of products with their logo, name, and price.
2. <b>Product Info Page</b>
 - Displays detailed information for a selected product, including logo, name, price, and description.
 - Includes "Add to Cart" and "Buy Now" buttons.
3. <b>Cart Functionality</b>
 - Adds selected items to the cart and displays a badge with the item count.
 - Allows removing items from the cart.

## API
> Products API: <https://my-json-server.typicode.com/carry1stdeveloper/mock-product-api/productBundles>

## Libraries & Plugins
- <b>Jetpack Compose </b>: For building the UI in a declarative manner.
- <b>Koin </b>: For dependency injection to manage dependencies efficiently.
- <b>Retrofit </b>: For network operations to fetch data from the API.
- <b>Room </b>: For local(offline) storage.
- <b>List-Detail Layout </b>: For a dual-pane layout where one pane presents a list of items and another pane displays the details of items selected from the list.
- <b>App Search </b>: a high-performance on-device search solution for managing locally stored, structured data.
- <b>Instantiator </b>: For generating test data from data classes.
- <b>Coil </b>: For image loading and caching.
- <b>JUnit </b>: For unit testing.
- <b>Mockito </b>: For mocking dependencies in tests.

Other dependencies are listed in the build.gradle files.

>![](https://github.com/user-attachments/assets/f4108f90-cec9-4895-91d7-e79542378149)

