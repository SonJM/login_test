<template>
  <div class="protected" v-if="loginSuccess">
    <h1>
      <b-badge variant="success">보안 사이트에 대한 액세스가 허용되었습니다</b-badge>
    </h1>
    <h5>로그인 성공!</h5>
  </div>
  <div class="unprotected" v-else-if="loginError">
    <h1>
      <b-badge variant="danger">이 페이지에 대한 접근 권한이 없습니다.</b-badge>
    </h1>
    <h5>로그인 실패!</h5>
  </div>
  <div class="unprotected" v-else>
    <h1>
      <b-badge variant="info">로그인해주세요</b-badge>
    </h1>
    <h5>로그인 하지 않았습니다. 로그인을 해주세요</h5>

    <form @submit.prevent="login()">
      <label>
        <input type="text" placeholder="email" v-model="email">
      </label>
      <label>
        <input type="password" placeholder="password" v-model="password">
      </label>
      <b-btn variant="success" type="submit">Login</b-btn>
      <p v-if="error" class="error">Bad login information</p>
    </form>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'login',
  data() {
    return {
      loginSuccess: false,
      loginError: false,
      email: '',
      password: '',
      error: false
    }
  },
  methods: {
    async login() {
      try {
        const result = await axios.get('/auth/login', {
          auth: {
            email: this.email,
            password: this.password
          }
        });
        if (result.status === 200) {
          this.loginSuccess = true
        }
      } catch (err) {
        this.loginError = true;
        throw new Error(err)
      }
    }
  }
}

</script>