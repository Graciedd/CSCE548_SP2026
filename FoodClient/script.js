/* ==========================================
   BASE CONFIG
========================================== */

let BASE_URL = "http://localhost:8080";

let currentRestaurant = null;
let currentUser = 1;
let orderItems = [];
let total = 0;


/* ==========================================
   LOAD RESTAURANTS FOR CUSTOMER VIEW
   Calls: GET /restaurants
========================================== */
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
            <button onclick="loadMenu(${r.restaurantId})">
                View Menu
            </button>
        </div>
        `;

    });

}

/* =========================
   LOAD USERS INTO DROPDOWN
========================= */
async function loadUsers() {

    const response = await fetch(`${BASE_URL}/users`);
    const users = await response.json();

    const select = document.getElementById("userSelect");
    select.innerHTML = "";

    users.forEach(u => {

        select.innerHTML += `
        <option value="${u.userId}">
            ${u.name} (ID: ${u.userId})
        </option>
        `;

    });

}


/* ==========================================
   LOAD MENU ITEMS FOR A RESTAURANT
   Calls: GET /menu/restaurant/{id}
========================================== */
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
            <button onclick="addToOrder('${item.name}', ${item.price})">
                Add
            </button>
        </div>
        `;

    });

}


/* ==========================================
   ADD ITEM TO ORDER CART
========================================== */
function addToOrder(name, price) {

    orderItems.push({ name, price });
    total += price;

    updateOrder();

}


/* ==========================================
   UPDATE ORDER CART DISPLAY
========================================== */
function updateOrder() {

    const container = document.getElementById("orderItems");
    container.innerHTML = "";

    orderItems.forEach(item => {

        container.innerHTML += `<p>${item.name} - $${item.price}</p>`;

    });

    document.getElementById("orderTotal").innerText = total.toFixed(2);

}


/* ==========================================
   PLACE ORDER
   Calls: POST /orders
========================================== */
async function placeOrder() {

    if (orderItems.length === 0) {
        alert("No items in order");
        return;
    }

    const order = {

        userId: currentUser,
        restaurantId: currentRestaurant,
        status: "Pending",
        totalAmount: Number(total.toFixed(2))

    };

    const response = await fetch(`${BASE_URL}/orders`, {

        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(order)

    });

    if (!response.ok) {

        const err = await response.text();
        alert("Order failed: " + err);
        return;

    }

    alert("Order placed!");

    orderItems = [];
    total = 0;

    updateOrder();

}


/* ==========================================
   GENERIC TABLE DISPLAY
   Used by admin/testing panel
========================================== */
function displayTable(containerId, data) {

    const container = document.getElementById(containerId);
    container.innerHTML = "";

    if (!Array.isArray(data)) {
        data = [data];
    }

    let table = "<table border='1'><tr>";

    Object.keys(data[0]).forEach(key => {

        table += `<th>${key}</th>`;

    });

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


/* =====================================================
   USER CRUD FUNCTIONS
   Uses UserController
===================================================== */

/* GET ALL USERS */
async function getAllUsers() {

    const response = await fetch(`${BASE_URL}/users`);
    const data = await response.json();

    displayTable("usersOutput", data);

}


/* GET USER BY ID */
async function getUserById() {

    const id = document.getElementById("userIdInput").value;

    const response = await fetch(`${BASE_URL}/users/${id}`);
    const data = await response.json();

    displayTable("usersOutput", data);

}


/* CREATE USER */
async function createUser() {

    const user = {

        name: document.getElementById("userName").value,
        email: document.getElementById("userEmail").value,
        address: document.getElementById("userAddress").value

    };

    await fetch(`${BASE_URL}/users`, {

        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(user)

    });

    alert("User created");

}


/* UPDATE USER */
async function updateUser() {

    const id = document.getElementById("userIdInput").value;

    const user = {

        name: document.getElementById("userName").value,
        email: document.getElementById("userEmail").value,
        address: document.getElementById("userAddress").value

    };

    await fetch(`${BASE_URL}/users/${id}`, {

        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(user)

    });

    alert("User updated");

}


/* DELETE USER */
async function deleteUser() {

    const id = document.getElementById("userIdInput").value;

    await fetch(`${BASE_URL}/users/${id}`, {

        method: "DELETE"

    });

    alert("User deleted");

}


/* VIEW USER ORDER HISTORY */
async function getOrdersByUser() {

    const id = document.getElementById("userIdInput").value;

    const response = await fetch(`${BASE_URL}/orders/user/${id}`);
    const data = await response.json();

    displayTable("ordersOutput", data);

}
/* CHANGE USER */
function changeUser() {

    const select = document.getElementById("userSelect");
    currentUser = select.value;

    document.getElementById("currentUserDisplay").innerText = currentUser;

}



/* =====================================================
   RESTAURANT CRUD
===================================================== */

async function createRestaurant() {

    const r = {

        name: document.getElementById("restaurantName").value,
        address: document.getElementById("restaurantAddress").value,
        active: true

    };

    await fetch(`${BASE_URL}/restaurants`, {

        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(r)

    });

    alert("Restaurant created");

}


async function updateRestaurant() {

    const id = document.getElementById("restaurantIdInput").value;

    const r = {

        name: document.getElementById("restaurantName").value,
        address: document.getElementById("restaurantAddress").value,
        active: true

    };

    await fetch(`${BASE_URL}/restaurants/${id}`, {

        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(r)

    });

    alert("Restaurant updated");

}

/* ==========================================
   RESTAURANT READ METHODS
========================================== */

/* GET ALL RESTAURANTS */
async function getAllRestaurants() {

    const response = await fetch(`${BASE_URL}/restaurants`);
    const data = await response.json();

    displayTable("restaurantsOutput", data);

}


/* GET RESTAURANT BY ID */
async function getRestaurantById() {

    const id = document.getElementById("restaurantIdInput").value;

    const response = await fetch(`${BASE_URL}/restaurants/${id}`);
    const data = await response.json();

    displayTable("restaurantsOutput", data);

}


async function deleteRestaurant() {

    const id = document.getElementById("restaurantIdInput").value;

    await fetch(`${BASE_URL}/restaurants/${id}`, {

        method: "DELETE"

    });

    alert("Restaurant deleted");

}



/* =====================================================
   MENU ITEM CRUD
===================================================== */

async function createMenuItem() {

    const item = {

        restaurantId: document.getElementById("menuRestaurantId").value,
        name: document.getElementById("menuName").value,
        price: document.getElementById("menuPrice").value

    };

    await fetch(`${BASE_URL}/menu`, {

        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(item)

    });

    alert("Menu item created");

}


async function updateMenuItem() {

    const id = document.getElementById("menuItemIdInput").value;

    const item = {

        name: document.getElementById("menuName").value,
        price: document.getElementById("menuPrice").value

    };

    await fetch(`${BASE_URL}/menu/${id}`, {

        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(item)

    });

    alert("Menu item updated");

}


async function deleteMenuItem() {

    const id = document.getElementById("menuItemIdInput").value;

    await fetch(`${BASE_URL}/menu/${id}`, {

        method: "DELETE"

    });

    alert("Menu item deleted");

}

/* ==========================================
   MENU ITEM READ METHODS
========================================== */

/* GET ALL MENU ITEMS */
async function getAllMenuItems() {

    const response = await fetch(`${BASE_URL}/menu`);
    const data = await response.json();

    displayTable("menuOutput", data);

}


/* GET MENU ITEM BY ID */
async function getMenuItemById() {

    const id = document.getElementById("menuItemIdInput").value;

    const response = await fetch(`${BASE_URL}/menu/${id}`);
    const data = await response.json();

    displayTable("menuOutput", data);

}


/* =====================================================
   ORDER MANAGEMENT
===================================================== */

/* GET ALL ORDERS */
async function getAllOrders() {

    const response = await fetch(`${BASE_URL}/orders`);
    const data = await response.json();

    displayTable("ordersOutput", data);

}


/* UPDATE ORDER STATUS */
async function updateOrderStatus() {

    const id = document.getElementById("orderIdInput").value;
    const status = document.getElementById("orderStatus").value;

    const response = await fetch(`${BASE_URL}/orders/${id}/status`, {

        method: "PUT",
        headers: {
            "Content-Type": "text/plain"
        },
        body: status

    });

    const msg = await response.text();
    alert(msg);

}


/* DELETE ORDER */
async function deleteOrder() {

    const id = document.getElementById("orderIdInput").value;

    await fetch(`${BASE_URL}/orders/${id}`, {

        method: "DELETE"

    });

    alert("Order deleted");

}



/* ==========================================
   INITIAL PAGE LOAD
========================================== */

window.onload = () => {
    loadRestaurants();
    loadUsers();
};