package components

import com.ccfraser.muirwik.components.MColor
import com.ccfraser.muirwik.components.MTypographyVariant
import com.ccfraser.muirwik.components.button.MButtonVariant
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.button.variant
import com.ccfraser.muirwik.components.mTypography
import kotlinx.css.padding
import kotlinx.css.px
import mappers.CounterProps
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyleSheet
import styled.css
import styled.styledDiv

object CounterStyles : StyleSheet("CounterStyles") {
  val button by css {
    padding(10.px)
  }
}

class Counter(props: CounterProps) : RComponent<CounterProps, RState>(props) {
  override fun RBuilder.render() {
    styledDiv {
      css { +CounterStyles.button }
      mButton(
        "Inc Counter ${props.counterType}",
        color = MColor.primary,
        variant = MButtonVariant.contained,
        onClick = { props.setCounter(props.counter + 1) }
      )
    }
    mTypography(variant = MTypographyVariant.h6, component = "h6") {
      +"Counter = ${props.counter}"
    }
  }
}