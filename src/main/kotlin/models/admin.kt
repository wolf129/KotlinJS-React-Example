package models

enum class CounterType {
  COUNTER1, COUNTER2
}

data class AdminState(
  val counter1: Int,
  val counter2: Int
)
