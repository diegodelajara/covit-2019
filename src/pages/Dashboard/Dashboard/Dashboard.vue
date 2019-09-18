<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <div class="card card-stats card-raised">
          <div class="card-body">
            <div class="row">
              <div class="col-md-3 col-6">
                <div class="statistics">
                  <div class="info">
                    <p v-show="$isAllowed('viewParagraph')">
                      Hide
                    </p>
                    <div class="icon icon-primary">
                      <i class="now-ui-icons ui-2_chat-round"></i>
                    </div>
                    <h3 class="info-title">
                      <animated-number :value="853"></animated-number>
                    </h3>
                    <h6 class="stats-title">Messages</h6>
                  </div>
                </div>
              </div>
              <div class="col-md-3 col-6">
                <div class="statistics">
                  <div class="info">
                    <div class="icon icon-success">
                      <i class="now-ui-icons business_money-coins"></i>
                    </div>
                    <h3 class="info-title">
                      <small>$</small>
                      <animated-number :value="3521"></animated-number>
                    </h3>
                    <h6 class="stats-title">Today Revenue</h6>
                  </div>
                </div>
              </div>
              <div class="col-md-3 col-6">
                <div class="statistics">
                  <div class="info">
                    <div class="icon icon-info">
                      <i class="now-ui-icons users_single-02"></i>
                    </div>
                    <h3 class="info-title">
                      <animated-number :value="562"></animated-number>
                    </h3>
                    <h6 class="stats-title">Customers</h6>
                  </div>
                </div>
              </div>
              <div class="col-md-3 col-6">
                <div class="statistics">
                  <div class="info">
                    <div class="icon icon-danger">
                      <i class="now-ui-icons objects_support-17"></i>
                    </div>
                    <h3 class="info-title">
                      <animated-number :value="353"></animated-number>
                    </h3>
                    <h6 class="stats-title">Support Requests</h6>
                  </div>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-lg-4">
        <card class="card-chart" no-footer-line>
          <div slot="header">
            <h5 class="card-category">Active Users</h5>
            <h2 class="card-title">
              <animated-number :value="34252">

              </animated-number>
            </h2>
            <drop-down :hide-arrow="true" position="right">
              <n-button slot="title" class="dropdown-toggle no-caret" round simple icon>
                <i class="now-ui-icons loader_gear"></i>
              </n-button>

              <a class="dropdown-item" href="#">Action</a>
              <a class="dropdown-item" href="#">Another action</a>
              <a class="dropdown-item" href="#">Something else here</a>
              <a class="dropdown-item text-danger" href="#">Remove Data</a>
            </drop-down>

          </div>
          <div class="chart-area">
            <line-chart :labels="charts.activeUsers.labels"
                       :data="charts.activeUsers.data"
                       :color="charts.activeUsers.color"
                       :height="200">
            </line-chart>
          </div>
          <div slot="footer" class="stats">
            <i class="now-ui-icons arrows-1_refresh-69"></i> Just Updated
          </div>
        </card>
      </div>

      <div class="col-lg-4">
        <card class="card-chart" no-footer-line>
          <div slot="header">
            <h5 class="card-category">Summer Email Campaign</h5>
            <h2 class="card-title">
              <animated-number :value="55300">
              </animated-number>
            </h2>
            <drop-down position="right">
              <n-button slot="title" class="dropdown-toggle no-caret" round simple icon>
                <i class="now-ui-icons loader_gear"></i>
              </n-button>

              <a class="dropdown-item" href="#">Action</a>
              <a class="dropdown-item" href="#">Another action</a>
              <a class="dropdown-item" href="#">Something else here</a>
              <a class="dropdown-item text-danger" href="#">Remove Data</a>
            </drop-down>

          </div>
          <div class="chart-area">
            <line-chart :labels="charts.emailsCampaign.labels"
                       :data="charts.emailsCampaign.data"
                       :color="charts.emailsCampaign.color"
                       :height="200">
            </line-chart>
          </div>
          <div slot="footer" class="stats">
            <i class="now-ui-icons arrows-1_refresh-69"></i> Just Updated
          </div>
        </card>
      </div>

      <div class="col-lg-4">
        <card class="card-chart" no-footer-line>
          <div slot="header">
            <h5 class="card-category">Active Countries</h5>
            <h2 class="card-title">
              <animated-number :value="105">
              </animated-number>
            </h2>
          </div>
          <div class="chart-area">
            <line-chart :labels="charts.activeCountries.labels"
                       :data="charts.activeCountries.data"
                       :color="charts.activeCountries.color"
                       :height="200">
            </line-chart>
          </div>


          <div slot="footer" class="stats">
            <i class="now-ui-icons arrows-1_refresh-69"></i> Just Updated
          </div>
        </card>
      </div>

    </div>
    <div class="row">
      <div class="col-md-12">
        <card>

        </card>
      </div>
    </div>

  </div>
</template>
<script>
  import {
    StatsCard,
    Card,
    Checkbox,
    AnimatedNumber,
    LineChart
  } from 'src/components'

  export default {
    components: {
      Checkbox,
      Card,
      StatsCard,
      AnimatedNumber,
      LineChart
    },
    data() {
      return {
        progress: 0,
        charts: {
          activeUsers: {
            labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            data: [542, 480, 430, 550, 530, 453, 380, 434, 568, 610, 700, 630],
            color: '#f96332'
          },
          emailsCampaign: {
            labels: ["12pm,", "3pm", "6pm", "9pm", "12am", "3am", "6am", "9am"],
            data: [40, 500, 650, 700, 1200, 1250, 1300, 1900],
            color: '#18ce0f'
          },
          activeCountries: {
            labels: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October"],
            data: [80, 78, 86, 96, 83, 85, 76, 75, 88, 90],
            color: '#2CA8FF'
          }
        }
      }
    }
  }
</script>
<style>

</style>
