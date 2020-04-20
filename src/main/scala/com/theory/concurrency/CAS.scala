package com.theory.concurrency

import scala.annotation.tailrec
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import org.scalacheck.Arbitrary
import org.scalacheck.Gen.Parameters

object CAS {

  private var uniqueID: Long = 0

  def compareAndSet(ov: Long, nv: Long): Boolean = this.synchronized {
    if (this.uniqueID == ov){
      uniqueID = nv
      true
    } else false
  }
  @tailrec
  def getUniqueID(): Long = {
    val oldValue = uniqueID
    val newValue = oldValue + 1
    if (compareAndSet(oldValue, newValue)) newValue
    else getUniqueID()
  }

}

class Caller {
  def getUniqueID: Long = CAS.getUniqueID()

}

object CallerSpecification extends Properties("myprops") {

  implicit val arbitraryPerson: Arbitrary[Caller] = Arbitrary { new Caller }

  val neverTheSame = forAll { (a: Caller, b: Caller) => a.getUniqueID != b.getUniqueID }

  val onlyIncrementOne = forAll { (a: Caller, b: Caller) => 
    (a.getUniqueID - b.getUniqueID).abs  == 1
  }
  neverTheSame.check
  onlyIncrementOne.check

}
