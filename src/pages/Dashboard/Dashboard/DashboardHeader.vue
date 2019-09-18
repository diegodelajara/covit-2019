<template>
  <div class="panel-header panel-header-lg">
    <header-chart :height="255"
                  :data="chartData"
                  :labels="chartLabels">
    </header-chart>
  </div>
</template>

<script>
  import HeaderChart from './HeaderChart'

  export default {
    name: 'overview-header',
    components: {
      HeaderChart
    },
    data(){
      return {
        chartData: [50, 150, 100, 190, 130, 90, 150, 160, 120, 140, 190, 95],
        chartLabels: ['ENE', 'FEB', 'MAR', 'ABR', 'MAY', 'JUN', 'JUL', 'AGO', 'SEP', 'OCT', 'NOV', 'DIC']
      }
    }
  }
</script>

<style scoped>

</style>
