# Order-Notification-System
Advanced Software Engineering Assignment-

This project is a component within a larger software system, focusing on managing orders and notifications. The module allows users to make online purchases, manage notifications, and provides a RESTful API for integration.

## Features

### Product Management
- Display a list of all available products.
- Products include a serial number, name, vendor, category, and price.
- Track the remaining parts count for each category.

### Customer Account Management
- Customers can create accounts and maintain a balance.
- The balance is used for future purchasing operations.

### Order Placement
- **Simple Orders**: Single product or multiple products.
- **Compound Orders**: Multiple orders combined to reduce shipping fees.
  - Example: A customer can order for themselves and friends in nearby locations.
- List details of both simple and compound orders.
- Deduct balances from corresponding customers for placed orders.

### Shipping Management
- **Simple Orders**: Deduct shipping fees from the customer's account.
- **Compound Orders**: Distribute shipping fees among participating customers.

### Notifications Management
- Create notifications for various operations.
- Manage notification templates, subjects, content, languages, and channels (email, SMS).
- Use placeholders in templates for dynamic content.
  - Example template: "Dear {x}, your booking of the {y} is confirmed. Thanks for using our store :)"
- Support at least two different templates for order placement and shipment.

### Notifications Queue
- Implement a queue for notifications to be sent.
- List the current content of the queue.

### Bonus Features
- Allow customers to cancel order placement or shipping within a pre-configured duration.
- Automatically remove messages from the queue after a configured time to simulate sending.
- Provide live statistics on notifications:
  - Most notified email address/phone number.
  - Most sent notification template.

## RESTful API

The module exposes all functionalities via a RESTful API. Below are examples of the API endpoints:

### Account Management
- **Add Account**: `POST /account/add`
  - Input: username, email, phone, and balance.
    
- **Get Account**: `GET /account/get/{username}`
  - Input: username
  - Output: All user info if exists

### Product Management
- **Get Product**: `GET /product/get/{ID}`
  - Output: Product details.
  
- **Get All Products**: `GET /product/getAll`
  - Output: List of all products


### Order Management
- **Place Simple Order**: `POST /order/add/simpleOrder`
  - Input: Order details
- **Place Compound Order**: `POST /order/add/compoundOrder`
  - Input: Order details

### Shipping Management
- **Ship Order**: `POST /order/ship/{ID}`
  - Input: Order ID

### Bonus Features API
- **Get Notification Statistics**: `GET /statistics/get/mostNotifiedEmail`
  - Output: Statistics data
- **Get Notification Statistics**: `GET /statistics/get/mostNotifiedSMS`
  - Output: Statistics data
- **Get Notification Statistics**: `GET /statistics/get/mostUsedTemplate`
  - Output: Statistics data

## Setup and Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/MohamedEssam71/Order-Notification-System.git


## Team members
| Name | ID         | Email                             | Linked-In |
|------|------------|-----------------------------------|-----------|
| Mohamed Essam Mahmoud Osman | `20210346` | messam.sde@gmail.com              | <a href = "https://www.linkedin.com/in/mohamed-essam71/">mohamed-essam71</a> |
| Mina Hakim | `20210xxx` | --                                | <a href = "--"> -- </a> |
| Alan Samir Hakoun | `20210xxx` | alanhakoun@gmail.com              | <a href = "https://www.linkedin.com/in/alan-hakoun/"> alan-hakoun </a> |
| Salah Eddin Mohamed | `20210187` | salaheddin.mohamed12345@gmail.com | <a href = "--"> -- </a> |
