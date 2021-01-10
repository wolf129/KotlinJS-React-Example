package reducers

import components.defaultUserState
import models.UserState
import redux.RAction

fun userReducer(state: UserState = defaultUserState, action: RAction) = when (action) {
  else -> state
}