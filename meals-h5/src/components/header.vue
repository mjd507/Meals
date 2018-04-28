<template>
  <div class="header">
    <div class="title">途虎订餐系统</div>
    <div class="user">{{userName}}</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userName: ''
    }
  },
  created() {
    this.getUser()
  },
  methods: {
    getUser() {
      this.fetch({
        url: this.apis.getUserName
      }).then((res) => {
        this.userName = res;
        this.userName = '马剑东'
      })
    },
    logout() {
      window.location = this.apis.logout
    }
  }
}
</script>

<style lang="less" scoped>
.header {

  height: 3rem;
  background: #d12a3e;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 2rem;
  .title {
    color: white;
    font-size: 1.2rem;
    font-weight: bold;
  }
  .user {
    color: white;
    font-size: 1rem;
  }
}
</style>
