<template>
  <form>
    <div>
      <fg-input type="text"
                required
                name="nombreIngreso"
                label="Nombre"
                v-model="selectedEntry.title">
      </fg-input>
      <fg-input type="text"
                required
                name="residenciangreso"
                label="Residencia"
                v-model="selectedEntry.department">
      </fg-input>
      <fg-input type="text"
                required
                name="conceptoIngreso"
                label="Concepto"
                v-model="selectedEntry.concept">
      </fg-input>
      <!-- <div class="row">
        <span>Tipo de Ingreso</span>
        <el-select label="Tipo de ingreso" class="select-default" placeholder="Selecciones un tipo de ingreso" v-model="formData.entryType">
          <el-option v-for="(option, key) in entryOfTypes" class="select-default" :value="option.value" :label="option.name" :key="key">
          </el-option>
        </el-select>
      </div> -->
      
      <div class="row">
        <div class="col-md-6">
          <fg-input label="Monto"
                    required
                    type="number"
                    name="monto"
                    v-model="selectedEntry.amount">
          </fg-input>
        </div>
        <!-- <div class="col-md-6">
          <span>Métodos de pago</span>
          <el-select label="Métodos de pago" class="select-default" placeholder="Selecciones un método de pago" v-model="formData.wayToPay" v-if="payMethods">
            <el-option v-for="(option, key) in payMethods.methods" class="select-default" :value="option.value" :label="option.name" :key="key">
            </el-option>
          </el-select>
        </div> -->
      </div>
    
      <fg-input>
        <label for="">Fecha</label>
        <el-date-picker v-model="selectedEntry.paymentDate" type="datetime" placeholder="Seleccione una fecha">
        </el-date-picker>
      </fg-input>

      <div class="row">
        <div class="col-md-12">
          <textarea v-model="selectedEntry.comments" placeholder="Comentario"></textarea>
        </div>
      </div>

    </div>
    <div slot="footer" class="text-right">
      <n-button native-type="submit" @click.native.prevent="validate" type="info">Guardar</n-button>
    </div>
  </form>
</template>

<script>
import axios from "axios";
import { mapState, mapGetters, mapMutations } from "vuex"
import { payMethodsConst, entryOfTypesConst } from "src/utils/helpers"
import { Select, Option, DatePicker } from "element-ui"
import { varToEmpty } from "src/utils/functions"
import { NEW_ENTRY } from 'src/constants/events'
import { stat } from 'fs';

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
      payMethods: null,
      entryOfTypes: null
    };
  },
  methods: {
    validate() {
      let newEntry = NEW_ENTRY(this.selectedEntry)
      newEntry.id = this.selectedEntry.id      
      this.$emit('on-finish-edit-entry', newEntry)
    }
  },
  computed: {
    ...mapState({
      selectedEntry: state => state.contabilidad.selectedEntry
    })
  }
}
</script>
<style scoped>
textarea {
  width: 100%;
}
</style>
