package reducers

import actions.SetButton1Counter
import actions.SetButton2Counter
import components.defaultAdminState
import models.AdminState
import redux.RAction

fun adminReducer(state: AdminState = defaultAdminState, action: RAction) = when (action) {
  is SetButton1Counter -> state.copy(counter1 = action.counter)
  is SetButton2Counter -> state.copy(counter2 = action.counter)
  else -> state
}