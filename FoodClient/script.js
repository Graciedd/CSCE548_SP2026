// =============================
// Food Delivery Web Client
// =============================

// Change this if your backend runs on another port
let BASE_URL = "http://localhost:8080";

// Allow user to change base URL from input field
function setBaseUrl() {
    BASE_URL = document.getElementById("baseUrl").value;
    alert("Base URL set to: " + BASE_URL);
}

// Utility function to display JSON nicely
function displayResult(elementId, data) {
    document.getElementById(elementId).textContent =
        JSON.stringify(data, null, 2);
}

// =============================
// USERS
// =============================

async function getAllUsers() {
    try {
        const response = await fetch(`${BASE_URL}/users`);
        const data = await response.json();
        displayResult("usersOutput", data);
    } catch (error) {
        displayResult("usersOutput", error);
    }
}

async function getUserById() {
    const id = document.getElementById("userId").value;

    try {
        const response = await fetch(`${BASE_URL}/users/${id}`);
        const data = await response.json();
        displayResult("usersOutput", data);
    } catch (error) {
        displayResult("usersOutput", error);
    }
}

// =============================
// RESTAURANTS
// =============================

async function getAllRestaurants() {
    try {
        const response = await fetch(`${BASE_URL}/restaurants`);
        const data = await response.json();
        displayResult("restaurantsOutput", data);
    } catch (error) {
        displayResult("restaurantsOutput", error);
    }
}

async function getRestaurantById() {
    const id = document.getElementById("restaurantId").value;

    try {
        const response = await fetch(`${BASE_URL}/restaurants/${id}`);
        const data = await response.json();
        displayResult("restaurantsOutput", data);
    } catch (error) {
        displayResult("restaurantsOutput", error);
    }
}

// =============================
// MENU ITEMS
// =============================

async function getAllMenuItems() {
    try {
        const response = await fetch(`${BASE_URL}/menu`);
        const data = await response.json();
        displayResult("menuOutput", data);
    } catch (error) {
        displayResult("menuOutput", error);
    }
}

async function getMenuByRestaurant() {
    const id = document.getElementById("menuRestaurantId").value;

    try {
        const response = await fetch(`${BASE_URL}/menu/restaurant/${id}`);
        const data = await response.json();
        displayResult("menuOutput", data);
    } catch (error) {
        displayResult("menuOutput", error);
    }
}

async function getMenuItemById() {
    const id = document.getElementById("menuItemId").value;

    try {
        const response = await fetch(`${BASE_URL}/menu/${id}`);
        const data = await response.json();
        displayResult("menuOutput", data);
    } catch (error) {
        displayResult("menuOutput", error);
    }
}

// =============================
// ORDERS
// =============================

async function getAllOrders() {
    try {
        const response = await fetch(`${BASE_URL}/orders`);
        const data = await response.json();
        displayResult("ordersOutput", data);
    } catch (error) {
        displayResult("ordersOutput", error);
    }
}

async function getOrdersByUser() {
    const id = document.getElementById("orderUserId").value;

    try {
        const response = await fetch(`${BASE_URL}/orders/user/${id}`);
        const data = await response.json();
        displayResult("ordersOutput", data);
    } catch (error) {
        displayResult("ordersOutput", error);
    }
}

async function getOrderById() {
    const id = document.getElementById("orderId").value;

    try {
        const response = await fetch(`${BASE_URL}/orders/${id}`);
        const data = await response.json();
        displayResult("ordersOutput", data);
    } catch (error) {
        displayResult("ordersOutput", error);
    }
}