package middleware

import actions.SetButton2Counter
import store.Dispatch
import store.GetState
import store.RThunk

class LoadList : RThunk {
  override suspend fun invoke(dispatch: Dispatch, getState: GetState) {
    console.log("Load List invoked ;) :P")
    dispatch(SetButton2Counter(99))
  }
}
