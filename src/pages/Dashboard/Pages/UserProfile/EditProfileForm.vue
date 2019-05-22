<template>
  <card>
    <h5 slot="header" class="title">Mi Perfil</h5>
    <form>

      <div class="row">
        <div class="col-md-6">
          <fg-input type="text"
                    label="Nombre"
                    placeholder="Nombre"
                    v-model="user.firstName">
          </fg-input>
        </div>
        <div class="col-md-6">
          <fg-input type="text"
                    label="Apellido"
                    placeholder="Apellido"
                    v-model="user.lastName">
          </fg-input>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6">

          <fg-input type="text"
                    label="Nombre de usuario"
                    placeholder="Username"
                    v-model="user.username">
          </fg-input>
        </div>
        <div class="col-md-6">
          <fg-input type="email"
                    label="Correo electrónico"
                    placeholder="Email"
                    v-model="user.email">
          </fg-input>
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <fg-input type="text"
                    label="Dirección"
                    placeholder="Dirección"
                    v-model="user.address">
          </fg-input>
        </div>
      </div>
    </form>
  </card>
</template>
<script>
export default {
  data() {
    return {
      user: {
        company: "Creative Code Inc.",
        username: "michael23",
        email: "",
        firstName: "Mike",
        lastName: "Andrew",
        address: "Bld Mihail Kogalniceanu, nr. 8 Bl 1, Sc 1, Ap 09"
      }
    };
  },
  methods: {
    updateProfile() {
      alert("Your data: " + JSON.stringify(this.user));
    }
  }
};
</script>
<style>
</style>
