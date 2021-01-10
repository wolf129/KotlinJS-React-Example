package mappers

import actions.SetButton1Counter
import actions.SetButton2Counter
import components.store
import middleware.LoadList
import models.CounterType
import components.Counter as CounterComponent
import react.RClass
import react.RProps
import react.invoke
import react.rClass
import react.redux.rConnect
import reducers.AppState
import redux.RAction
import redux.WrapperAction
import selectors.getCounter1
import selectors.getCounter2
import store.Dispatch

interface CounterOwnProps : RProps {
  var counterType: CounterType
}

interface CounterStateProps : RProps {
  var counterType: CounterType
  var counter: Int
}

interface CounterDispatchProps : RProps {
  var setCounter: (Int) -> Unit
}

interface CounterProps : RProps {
  var counterType: CounterType
  var counter: Int
  var setCounter: (Int) -> Unit
}

object CounterMapper: Mapping<CounterOwnProps, CounterStateProps, CounterDispatchProps, CounterProps, CounterComponent> {
  override val mapStateToProps: CounterStateProps.(AppState, CounterOwnProps) -> Unit = { state, ownProps ->
    counter = when (ownProps.counterType) {
      CounterType.COUNTER1 -> getCounter1(state)
      CounterType.COUNTER2 -> getCounter2(state)
    }
    counterType = ownProps.counterType
  }
  override val mapDispatchToProps: CounterDispatchProps.(Dispatch, CounterOwnProps) -> Unit =
    { dispatch, ownProps ->
      setCounter = {
        when(ownProps.counterType) {
          CounterType.COUNTER1 -> { dispatch(LoadList()); dispatch(SetButton1Counter(it)) }
          CounterType.COUNTER2 -> dispatch(SetButton2Counter(it))
        }
      }
    }
  override val connector: RClass<CounterOwnProps> = createDefault(CounterComponent::class)
}

val Counter = CounterMapper.connector