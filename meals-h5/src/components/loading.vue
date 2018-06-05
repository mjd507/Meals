<template >
  <div class="layout-loading">
    <div class="loading-content">
      <div class="loading-dot">
        <div class="dot1"></div>
        <div class="dot2"></div>
        <div class="dot3"></div>
      </div>
      <!-- <div>加载中...</div> -->
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      showLoginLayout: false
    }
  }
}
</script>
 <style lang="less" scoped>
.layout-loading {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 2000;
  background: rgba(255,255,255,0.5);
  .loading-content {
    position: absolute;
    width: 22rem;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    text-align: center;
    color: #666;
    font-size: 1.2rem;
  }
  .loading-dot {
    display: flex;
    justify-content: center;
    .dot1,
    .dot2,
    .dot3 {
      width: 0.8rem;
      height: 0.8rem;
      border-radius: 50%;
      background-color: rgba(0, 0, 0, 0.5);
      margin: 0 0.8rem;
      animation: dot-animate 0.9s linear infinite normal;
    }
    .dot2 {
      background-color: rgba(0, 0, 0, 0.3);
      animation-delay: 0.3s;
    }
    .dot3 {
      background-color: rgba(0, 0, 0, 0.1);
      animation-delay: 0.6s;
    }
  }
}

@keyframes dot-animate {
  0%,
  100% {
    background-color: rgba(0, 0, 0, 0.5);
  }
  50% {
    background-color: rgba(0, 0, 0, 0.1);
  }
}
</style>
