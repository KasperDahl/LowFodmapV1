<!-- <template>
    <div>
      <h1>Special Items</h1>     
    </div>
    <div>
        <ul>
            <li v-for="specialItem in specialItems" :key="specialItem.name">
                {{ specialItem.name }}
            </li>
        </ul>
    </div>
    <div>
        <button>Get all Special Items</button>
    </div>
</template> -->
<!-- <template>
  <div>
    <h1>Special Items</h1>
    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Brand</th>
          <th>Size</th>
          <th>Category</th>
          <th>Shop</th>
          <th>Price</th>
          <th>Note</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="specialItem in specialItems" :key="specialItem.name">
          <td>{{ specialItem.name }}</td>
          <td>{{ specialItem.brand }}</td>
          <td>{{ specialItem.size }}</td>
          <td>{{ specialItem.category }}</td>
          <td>
            <select v-model="specialItem.selectedShop">
              <option v-for="shop in specialItem.shop" :key="shop" :value="shop">{{ shop }}</option>
            </select>
          </td>
          <td>
            <select v-model="specialItem.selectedPrice">
              <option v-for="price in specialItem.price" :key="price" :value="price">{{ price }}</option>
            </select>
          </td>
          <td>{{ specialItem.note }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template> -->

<template>
  <div>
    <h1>Special Items</h1>
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
<!--           <td>
            <select v-model="specialItem.selectedShop" @change="syncDropdowns('shop', specialItem)">
              <option v-for="shop in specialItem.shop" :key="shop" :value="shop">{{ shop }}</option>
            </select>
          </td>
          <td>
            <select v-model="specialItem.selectedPrice" @change="syncDropdowns('price', specialItem)">
              <option v-for="price in specialItem.price" :key="price" :value="price">{{ price }}</option>
            </select>
          </td> -->
          <td>{{ specialItem.note }}</td>
        </tr>
      </tbody>
    </table>
  </div>
  </div>
</template>

<script>
import { ref, computed, watch } from 'vue';

export default {
  data() {
    return {
      specialItems: [],
      sortColumn: '',
      sortOrder: 'asc',
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

<!-- <script>
export default {
  data() {
    return {
      specialItems: [],
    };
  },
  created() {
    this.fetchSpecialItems();
  },
  methods: {
    // This method is used to fetch all special items from the server.
    // It is called when the page is loaded.
    fetchSpecialItems() {
      fetch("/api/specialitems")
        .then((response) => response.json())
        .then((data) => {
          this.specialItems = data;
        });
    },
  },
};
</script> -->

