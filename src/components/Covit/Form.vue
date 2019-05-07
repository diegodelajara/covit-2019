<template>
  <form>
    <div>
      <fg-input type="text"
                required
                name="conceptoIngreso"
                label="Concepto"
                v-model="formData.concept">
      </fg-input>
      <div class="row">
        <span>Métodos de pago</span>
        <el-select label="Tipo de entrada" class="select-default" placeholder="Selecciones un tipo de entrada" v-model="formData.entryType">
          <el-option v-for="(option, key) in entryOfTypes" class="select-default" :value="option.value" :label="option.name" :key="key">
          </el-option>
        </el-select>
      </div>
      
      <fg-input type="text"
                required
                name="residenciangreso"
                label="Residencia"
                v-model="formData.department">
      </fg-input>
      <fg-input type="text"
                required
                name="nombreIngreso"
                label="Nombre"
                v-model="formData.name">
      </fg-input>
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
            <el-option v-for="option in payMethods.methods" class="select-default" :value="option.id" :label="option.name" :key="option.id">
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
import axios from "axios";
import { mapState, mapMutations } from "vuex";
import { payMethodsConst, entryOfTypesConst } from "src/utils/helpers";
import { Select, Option, DatePicker } from "element-ui";

export default {
  components: {
    [Select.name]: Select,
    [Option.name]: Option,
    [DatePicker.name]: DatePicker
  },
  mounted() {
    this.payMethods = payMethodsConst;
    this.entryOfTypes = entryOfTypesConst;
  },
  data() {
    return {
      formData: {
        entryType: null,
        concept: null,
        department: null,
        name: null,
        amount: null,
        wayToPay: null,
        date: null,
        comments: null
      },
      payMethods: null,
      entryOfTypes: null
    };
  },
  methods: {
    ...mapMutations(["setEntry"]),
    async validate() {
      this.formData.wayToPay = await this.payMethods.methods[
        this.formData.wayToPay
      ];
      await this.setEntry(this.formData);
      const newEntry = {
        title: this.formData.name,
        entryType: "GASTOS_COMUNES",
        description: "monto 111",
        paymentNumber: "paymentNumber",
        receiptNumber: "receiptNumber",
        payerName: "payerName",
        concept: "concept",
        wayToPay: "EFECTIVO",
        paymentDate: "2019-05-01",
        amount: "111111111",
        gloss: "gloss",
        comments: "comments",
        respRegister: "respRegister",
        registrationDate: "2019-05-01",
        status: "ACTIVO",
        refDocument: "refDocument"
      };
      await axios
        .post("/api/entries", newEntry)
        .then(response => console.log(response))
        .catch(error => console.log(error));
    }
  }
  // computed: {
  //   ...mapState({
  //     formData: state => state.formData
  //   })
  // }
};
</script>
<style scoped>
textarea {
  width: 100%;
}
</style>
