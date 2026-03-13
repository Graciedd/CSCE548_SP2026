let BASE_URL = "http://localhost:8080";

let currentRestaurant = null;
let orderItems = [];
let total = 0;

/* =========================
   LOAD RESTAURANTS (UI)
========================= */
async function loadRestaurants() {
    const response = await fetch(`${BASE_URL}/restaurants`);
    const restaurants = await response.json();

    const container = document.getElementById("restaurantsList");
    container.innerHTML = "";

    restaurants.forEach(r => {
        container.innerHTML += `
        <div class="card">
            <h3>${r.name}</h3>
            <p>${r.address}</p>
            <button onclick="loadMenu(${r.restaurantId})">View Menu</button>
        </div>
        `;
    });
}

/* =========================
   LOAD MENU (UI)
========================= */
async function loadMenu(restaurantId) {
    currentRestaurant = restaurantId;

    const response = await fetch(`${BASE_URL}/menu/restaurant/${restaurantId}`);
    const menu = await response.json();

    const container = document.getElementById("menuList");
    container.innerHTML = "";

    menu.forEach(item => {
        container.innerHTML += `
        <div class="menu-item">
            <span>${item.name} - $${item.price}</span>
            <button onclick="addToOrder('${item.name}', ${item.price})">Add</button>
        </div>
        `;
    });
}

/* =========================
   ADD ITEM TO ORDER
========================= */
function addToOrder(name, price) {
    orderItems.push({ name, price });
    total += price;
    updateOrder();
}

/* =========================
   UPDATE ORDER CART
========================= */
function updateOrder() {
    const container = document.getElementById("orderItems");
    container.innerHTML = "";

    orderItems.forEach(item => {
        container.innerHTML += `<p>${item.name} - $${item.price}</p>`;
    });

    document.getElementById("orderTotal").innerText = total.toFixed(2);
}

/* =========================
   PLACE ORDER
========================= */
async function placeOrder() {
    if (orderItems.length === 0) {
        alert("No items in order");
        return;
    }

    const order = {
    userId: 1,
    restaurantId: currentRestaurant,
    status: "Pending",
    totalAmount: Number(total.toFixed(2)) // <-- send as number
    };

    try {
        const response = await fetch(`${BASE_URL}/orders`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(order)
        });

        if (!response.ok) {
            const errorData = await response.json();
            alert("Order failed: " + JSON.stringify(errorData));
            return;
        }

        alert("Order placed!");
        orderItems = [];
        total = 0;
        updateOrder();
    } catch (err) {
        console.error(err);
        alert("Error placing order. See console for details.");
    }
}

/* =========================
   HELPER: DISPLAY TABLE
========================= */
function displayTable(containerId, data) {
    const container = document.getElementById(containerId);
    container.innerHTML = "";

    if (!data) {
        container.innerHTML = "No data found.";
        return;
    }

    if (!Array.isArray(data)) {
        data = [data];
    }

    let table = "<table border='1' cellpadding='5' cellspacing='0'>";
    table += "<tr>";
    Object.keys(data[0]).forEach(key => { table += `<th>${key}</th>`; });
    table += "</tr>";

    data.forEach(obj => {
        table += "<tr>";
        Object.values(obj).forEach(value => {
            table += `<td>${value}</td>`;
        });
        table += "</tr>";
    });

    table += "</table>";
    container.innerHTML = table;
}

/* =========================
   USERS
========================= */
async function getAllUsers() {
    const response = await fetch(`${BASE_URL}/users`);
    const data = await response.json();
    displayTable("usersOutput", data);
}

async function getUserById() {
    const id = document.getElementById("userIdInput").value;
    const response = await fetch(`${BASE_URL}/users/${id}`);
    const data = await response.json();
    displayTable("usersOutput", data);
}

/* =========================
   RESTAURANTS
========================= */
async function getAllRestaurants() {
    const response = await fetch(`${BASE_URL}/restaurants`);
    const data = await response.json();
    displayTable("restaurantsOutput", data);
}

async function getRestaurantById() {
    const id = document.getElementById("restaurantIdInput").value;
    const response = await fetch(`${BASE_URL}/restaurants/${id}`);
    const data = await response.json();
    displayTable("restaurantsOutput", data);
}

/* =========================
   MENU ITEMS
========================= */
async function getAllMenuItems() {
    const response = await fetch(`${BASE_URL}/menu`);
    const data = await response.json();
    displayTable("menuOutput", data);
}

async function getMenuItemById() {
    const id = document.getElementById("menuItemIdInput").value;
    const response = await fetch(`${BASE_URL}/menu/${id}`);
    const data = await response.json();
    displayTable("menuOutput", data);
}

/* =========================
   ORDERS
========================= */
async function getAllOrders() {
    const response = await fetch(`${BASE_URL}/orders`);
    const data = await response.json();
    displayTable("ordersOutput", data);
}

async function getOrderById() {
    const id = document.getElementById("orderIdInput").value;
    const response = await fetch(`${BASE_URL}/orders/${id}`);
    const data = await response.json();
    displayTable("ordersOutput", data);
}

/* =========================
   INITIAL PAGE LOAD
========================= */
window.onload = loadRestaurants;