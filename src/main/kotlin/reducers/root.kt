package reducers

import components.defaultAdminState
import components.defaultUserState
import models.AdminState
import models.UserState
import util.combineReducers

// Default states do not matter here, only from the reducer parameter are relevant
data class AppState(
  val admin: AdminState = defaultAdminState,
  val user: UserState = defaultUserState,
)

fun rootReducer() = combineReducers(
  mapOf(
    AppState::admin to ::adminReducer,
    AppState::user to ::userReducer,
  )
)