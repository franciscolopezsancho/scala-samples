package com.theory.dsl


object MazeDSL {

  import MazeRules._
  import MazeStates._

  // TODO infix won't work in here
  Person[OutRoomDoorClosed]().openDoor.enterRoom.closeDoor

}

object MazeStates {

  sealed trait State

  trait InRoomDoorClosed extends State

  trait InRoomDoorOpen extends State

  trait OutRoomDoorOpen extends State

  trait OutRoomDoorClosed extends State

  case class Person[PState <: State]()

}

object MazeRules {

  import MazeStates._


  implicit class InRoomDoorClosedOps(val person: Person[InRoomDoorClosed]){

    def openDoor = Person[InRoomDoorOpen]()

  }

  implicit class InRoomDoorOpenOps(val person: Person[InRoomDoorOpen]){

    def closeDoor = Person[InRoomDoorClosed]()
    def leaveRoom = Person[OutRoomDoorOpen]()

  }

  implicit class OutRoomDoorClosedOps(val person: Person[OutRoomDoorClosed]){

    def openDoor = Person[OutRoomDoorOpen]()

  }

  implicit class OutRoomDoorOpenOps(val person: Person[OutRoomDoorOpen]){

    def closeDoor = Person[OutRoomDoorClosed]()
    def enterRoom = Person[InRoomDoorOpen]()

  }

}
