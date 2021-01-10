package selectors

import reducers.AppState

fun getCounter1(appState: AppState) = appState.admin.counter1
fun getCounter2(appState: AppState) = appState.admin.counter2