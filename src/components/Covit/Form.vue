<template>
  <form>
    {{ formData }}
    <card no-footer-line>
      <div>
        <fg-input type="text"
                  required
                  name="nombreIngreso"
                  label="Nombre"
                  v-model="formData.nombre">
        </fg-input>

        <fg-input label="Monto"
                  required
                  type="number"
                  name="monto"
                  v-model="formData.monto">
        </fg-input>

      </div>
      <div slot="footer" class="text-right">
        <n-button native-type="submit" @click.native.prevent="validate" type="info">Agregar</n-button>
      </div>

    </card>
  </form>
</template>

<script>
import { ingresosRef } from 'src/firebase'
import { mapState } from 'vuex'
import { Checkbox } from 'src/components/index'



  export default {
    components: {
      Checkbox
    },
    data() {
      return {
        model: {
            nombre: '',
            monto: null,
            email: '',
            password: '',
            confirmPassword: '',
            subscribe: false
        },
        modelValidations: {
          email: {
            required: true,
            email: true
          },
          password: {
            required: true,
            min: 5
          },
          confirmPassword: {
            required: true,
            confirmed: 'password'
          },
          subscribe: {
            required: true
          }
        }
      }
    },
    methods: {
      validate() {
        this.$store.commit('setFormData')
        //console.log(this.firebase.ingresosRef)
        ingresosRef.push(this.formData)
          
      }
    },
    computed: {
      ...mapState({
        formData: state => state.formData
      })
    }
  }
</script>
<style>
</style>
