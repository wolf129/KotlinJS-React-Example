package store

import kotlinext.js.js
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import reducers.AppState
import reducers.rootReducer
import redux.*

typealias Dispatch = (RAction) -> WrapperAction
typealias GetState = () -> AppState

interface RThunk : RAction {
  suspend operator fun invoke(
    dispatch: Dispatch,
    getState: GetState
  )
}

val emptyAction = js { }.unsafeCast<WrapperAction>()

fun rThunk() =
  applyMiddleware<AppState, RAction, WrapperAction, RAction, WrapperAction>(
    { store ->
      { next ->
        { action ->
          if (action is RThunk) {
            GlobalScope.launch { action(store::dispatch, store::getState) }
            emptyAction
          } else {
            next(action)
          }
        }
      }
    }
  )

fun configureStore() = createStore(
  rootReducer(),
  AppState(),
  compose(rThunk(), rEnhancer())
)

//  return createStore(
//    rootReducer(), AppState(), compose(
//      rEnhancer(),
//      js("if(window.__REDUX_DEVTOOLS_EXTENSION__ )window.__REDUX_DEVTOOLS_EXTENSION__ ();else(function(f){return f;});"),
//    )
//  )
