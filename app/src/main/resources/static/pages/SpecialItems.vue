<template>
  <div>
    <div class="d-flex justify-content-between align-items-center">
      <h1>Special Items</h1>
      <button class="btn btn-primary" @click="showForm = !showForm" >Add Item</button>
    </div>
    <form v-show="showForm" @submit.prevent="addItem">
    <input v-model="newItem.name" placeholder="Name">
    <input v-model="newItem.brand" placeholder="Brand">
    <input v-model="newItem.size" placeholder="Size">
    <input v-model="newItem.category" placeholder="Category">
    <input v-model="newItem.shop" placeholder="Shop">
    <input v-model="newItem.price" placeholder="Price">
    <input v-model="newItem.note" placeholder="Note">
    <button type="submit">OK</button>
  </form>
      <div class="table-responsive">
      <table class="table table-striped">
        <thead>
          <tr>
            <th @click="sortTable('name')">Name</th>
            <th @click="sortTable('brand')">Brand</th>
            <th @click="sortTable('size')">Size</th>
            <th @click="sortTable('category')">Category</th>
            <th @click="sortTable('shopPrice')">Shop&Price</th>
            <th>Note</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="specialItem in sortedSpecialItems" :key="specialItem.name">
            <td>{{ specialItem.name }}</td>
            <td>{{ specialItem.brand }}</td>
            <td>{{ specialItem.size }}</td>
            <td>{{ specialItem.category }}</td>
            <td>
              <select v-model="specialItem.selectedShopPrice" @change="syncDropdowns('shopPrice', specialItem)">
                <option v-for="shopPrice in specialItem.shopPrice" :key="shopPrice" :value="shopPrice">{{ shopPrice }}</option>
              </select>
            </td>

            <td>{{ specialItem.note }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
// import { ref, computed, watch } from 'vue';

export default {
  data() {
    return {
      specialItems: [],
      sortColumn: '',
      sortOrder: 'asc',

      showForm: false,
      newItem: {
        name: '',
        brand: '',
        size: '',
        category: '',
        shop: '',
        price: '',
        note: '',
      },

    };
  },
  created() {
    this.fetchSpecialItems();
  },

  methods: {
    // This method is used to fetch all special items from the server. It is called when the page is loaded.
    // Shop and price are combined into a single array for easier sorting. 
    fetchSpecialItems() {
      fetch("/api/specialitems")
        .then((response) => response.json())
        .then((data) => {
          this.specialItems = data.map(item => {
            item.shopPrice = item.shop.map((shop, index) => `${shop} - ${item.price[index]}`);
            item.selectedShopPrice = item.shopPrice[0];
            return item;
          });
        });
    },
    // This method is used to sort the table by the given column.
    sortTable(column) {
      if (this.sortColumn === column) {
        this.sortOrder = this.sortOrder === 'asc' ? 'desc' : 'asc';
      } else {
        this.sortColumn = column;
        this.sortOrder = 'asc';
      }
    },
    // This method is used to sync the dropdowns. When the shop dropdown is changed, the price dropdown is also changed.
    syncDropdowns(type, value) {
      this.specialItems.forEach((specialItem) => {
        if (type === 'shopPrice') {
          specialItem.selectedShopPrice = value;
        }
      });
    },

    async addItem() {
      const response = await fetch('api-url', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(this.newItem),
      });
      if (response.ok) {
        this.showForm = false;
        this.newItem = {
          name: '',
          brand: '',
          size: '',
          category: '',
          shop: '',
          price: '',
          note: '',
        };
      }
    },


  },
  computed: {
    // This computed property is used to sort the special items by the given column and order.
    sortedSpecialItems() {
      const column = this.sortColumn;
      const order = this.sortOrder === 'asc' ? 1 : -1;

      return this.specialItems.slice().sort((a, b) => {
        if (a[column] < b[column]) {
          return -1 * order;
        }
        if (a[column] > b[column]) {
          return 1 * order;
        }
        return 0;
      });
    },
  },
};
</script>
