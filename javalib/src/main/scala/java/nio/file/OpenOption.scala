sealed trait OpenOption
object OpenOption {
  case object CREATE_NEW extends OpenOption
  case object TRUNCATE_EXISTING extends OpenOption
}
