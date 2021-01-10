package mappers

import react.*
import react.redux.rConnect
import reducers.AppState
import redux.RAction
import redux.WrapperAction
import store.Dispatch
import kotlin.reflect.KClass

interface Mapping<Own, State, Dispatch, Property, Component>
        where State : RProps,
              Dispatch : RProps,
              Own : RProps,
              Property : RProps,
              Component : RComponent<Property, RState> {
  val mapStateToProps: MapStateToProps<State, Own>
  val mapDispatchToProps: MapDispatchToProps<Dispatch, Own>
  val connector: RClass<Own>

  fun createDefault(c: KClass<Component>): RClass<Own> =
    rConnect<AppState, RAction, WrapperAction, Own, State, Dispatch, Property>(
      mapStateToProps,
      mapDispatchToProps
    )(c.rClass)
}


typealias MapStateToProps<S, O> = S.(AppState, O) -> Unit
typealias MapDispatchToProps<D, O> = D.(Dispatch, O) -> Unit