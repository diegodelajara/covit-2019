<template>
  <form>
    <div>
      <fg-input type="text"
                required
                name="conceptoIngreso"
                label="Concepto"
                v-model="formData.nombre">
      </fg-input>
      <fg-input type="text"
                required
                name="residenciangreso"
                label="Residencia"
                v-model="formData.nombre">
      </fg-input>
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

      <el-select class="select-danger" placeholder="Single Select" v-model="selected" v-if="payMethods">
        <el-option v-for="option in payMethods.methods" class="select-danger" :value="option.value" :label="option.label" :key="option.label">
        </el-option>
      </el-select>
    </div>
    <div slot="footer" class="text-right">
      <n-button native-type="submit" @click.native.prevent="validate" type="info">Agregar</n-button>
    </div>
  </form>
</template>

<script>
import { ingresosRef } from 'src/firebase'
import { mapState } from 'vuex'
import { Checkbox } from 'src/components/index'
import { payMethodsConst } from 'src/utils/helpers'
import { Select, Option} from 'element-ui'


  export default {
    components: {
      Checkbox,
      [Select.name]: Select,
      [Option.name]: Option
    },
    mounted() {
      this.payMethods = payMethodsConst
    },
    data() {
      return {
        payMethods: null,
        selected: null,
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
