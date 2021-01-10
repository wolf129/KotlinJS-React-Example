package components

import mappers.Counter
import models.AdminState
import models.CounterType
import models.UserState
import react.RProps
import react.functionalComponent
import react.redux.provider
import store.configureStore
import styled.styledDiv

val defaultAdminState = AdminState(0, 0)
val defaultUserState = UserState(0)

val store = configureStore()

val App = functionalComponent<RProps> {
  provider(store) {
    styledDiv {
      Counter {
        attrs.counterType = CounterType.COUNTER1
      }
      Counter {
        attrs.counterType = CounterType.COUNTER2
      }
    }
  }
}