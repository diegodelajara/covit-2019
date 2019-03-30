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
    
      <fg-input>
        <label for="">Fecha</label>
        <el-date-picker v-model="dateTimePicker" type="datetime" placeholder="Seleccione una fecha">
        </el-date-picker>
      </fg-input>

      <p>Métodos de pago</p>
      <el-select class="select-danger" placeholder="Selecciones un método de pago" v-model="selected" v-if="payMethods">
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
import { mapState } from "vuex";
import { Checkbox } from "src/components/index";
import { payMethodsConst } from "src/utils/helpers";
import { Select, Option, DatePicker } from "element-ui";

export default {
  components: {
    Checkbox,
    [Select.name]: Select,
    [Option.name]: Option,
    [DatePicker.name]: DatePicker
  },
  mounted() {
    this.payMethods = payMethodsConst;
  },
  data() {
    return {
      payMethods: null,
      selected: null,
      model: {
        nombre: "",
        monto: null,
        email: "",
        password: "",
        confirmPassword: "",
        subscribe: false
      },
      dateTimePicker: "",
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
          confirmed: "password"
        },
        subscribe: {
          required: true
        }
      }
    };
  },
  methods: {
    validate() {
      this.$store.commit("setFormData");
    }
  },
  computed: {
    ...mapState({
      formData: state => state.formData
    })
  }
};
</script>
<style>
</style>
