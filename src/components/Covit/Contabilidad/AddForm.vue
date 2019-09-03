<template>
  <form>
    <div>
      <fg-input type="text"
                required
                name="nombreIngreso"
                label="Nombre"
                v-model="formData.name">
      </fg-input>
      <fg-input type="text"
                required
                name="residenciangreso"
                label="Residencia"
                v-model="formData.department">
      </fg-input>
      <fg-input type="text"
                required
                name="conceptoIngreso"
                label="Concepto"
                v-model="formData.concept">
      </fg-input>
      <div class="row">
        <span>Tipo de Ingreso</span>
        <el-select label="Tipo de ingreso" class="select-default" placeholder="Selecciones un tipo de ingreso" v-model="formData.entryType">
          <el-option v-for="(option, key) in entryOfTypes" class="select-default" :value="option.value" :label="option.name" :key="key">
          </el-option>
        </el-select>
      </div>

      <div class="row">
        <div class="col-md-6">
          <fg-input label="Monto"
                    required
                    type="number"
                    name="monto"
                    v-model="formData.amount">
          </fg-input>
        </div>
        <div class="col-md-6">
          <span>Métodos de pago</span>
          <el-select label="Métodos de pago" class="select-default" placeholder="Selecciones un método de pago" v-model="formData.wayToPay" v-if="payMethods">
            <el-option v-for="(option, key) in payMethods.methods" class="select-default" :value="option.value" :label="option.name" :key="key">
            </el-option>
          </el-select>
        </div>
      </div>

      <fg-input>
        <label for="">Fecha</label>
        <el-date-picker v-model="formData.date" type="datetime" placeholder="Seleccione una fecha">
        </el-date-picker>
      </fg-input>

      <div class="row">
        <div class="col-md-12">
          <textarea v-model="formData.comments" placeholder="Comentario"></textarea>
        </div>
      </div>

    </div>
    <div slot="footer" class="text-right">
      <n-button native-type="submit" @click.native.prevent="validate" type="info">Agregar</n-button>
    </div>
  </form>
</template>

<script>
import { payMethodsConst, entryOfTypesConst } from "src/utils/helpers"
import { Select, Option, DatePicker } from "element-ui"
import { varToEmpty } from "src/utils/functions"
import { NEW_ENTRY } from 'src/constants/events'

export default {
  components: {
    [Select.name]: Select,
    [Option.name]: Option,
    [DatePicker.name]: DatePicker
  },
  mounted() {
    this.payMethods = payMethodsConst
    this.entryOfTypes = entryOfTypesConst
  },
  data() {
    return {
      formData: {
        name: null,
        entryType: null,
        concept: null,
        name: null,
        amount: null,
        wayToPay: null,
        date: null,
        comments: null,
        department: null
      },
      payMethods: null,
      entryOfTypes: null
    };
  },
  methods: {
    async validate() {
      const newEntry = NEW_ENTRY(this.formData)

      await this.$emit('on-finish-add-entry', newEntry)
    }
  }
}
</script>
<style scoped>
textarea {
  width: 100%;
}
</style>
