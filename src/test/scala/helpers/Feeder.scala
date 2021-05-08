package helpers

import io.gatling.core.Predef.{exec, _}

import scala.util.Random

object Feeder {

  val contents = csv("data/content.csv").random.circular

  val defaultUserFeeder = Iterator.continually({
    Map(
      "imsi" -> "1234567890",
      "deviceKey" -> (Random.alphanumeric.take(10).mkString)
    )
  })

  val phonePrepaid = Iterator.continually({
    var phone : Long = 1100000000 + Random.nextInt(1100005000 - 1100000000 + 1)
    Map(
      "mobileNumber" -> phone
    ) ++ generateUidToken(String.valueOf(phone))
  })


  def generateUidToken(phoneNumber : String) = {
    var uid: String = generateUid.getUidMsisdn(phoneNumber)
    Map(
      "uid" -> uid
    )
  }

  object generateUid {
    def getUidMsisdn(phoneNumber: String): String = ???
  }

}