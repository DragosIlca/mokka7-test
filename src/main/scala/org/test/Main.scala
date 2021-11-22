package org.test

import com.sourceforge.snap7.moka7.{S7, S7Client}

object Main extends App {

	val s7Client = new S7Client()

	basicTest()

	private def getDIntTest() = {
		s7Client.ConnectTo("192.168.5.1", 0, 1)

		val bytes: Array[Byte] = Array.empty

		val i = s7Client.ReadArea(S7.S7AreaDB, 603, 36, 4, bytes)

		println("------------- DINTTEST --------------")

		for(byteIndex <- 0 to bytes.length - 4) {
			val i1 = S7.GetDIntAt(bytes, byteIndex)
			println(i1)
		}

		println("-------------------------------------")

		s7Client.Disconnect()
	}

	private def getWord34_36() = {
		s7Client.ConnectTo("192.168.5.1", 0, 1)

		val bytes: Array[Byte] = Array.empty

		val i = s7Client.ReadArea(S7.S7AreaDB, 603, 34, 2, bytes)

		println("------------- WORDTEST --------------")

		for(byteIndex <- 0 to bytes.length - 4) {
			val i1 = S7.GetWordAt(bytes, byteIndex)
			print(i1 + " ")
		}

		println("-------------------------------------")

		s7Client.Disconnect()
	}

	private def basicTest() = {
		s7Client.ConnectTo("192.168.5.1", 0, 1)

		val bytes: Array[Byte] = Array.empty

		val i = s7Client.ReadArea(S7.S7AreaDB, 600, 0, 1, bytes)

		println("------------- BASICTEST --------------")

		if (i == 0) {
			println(S7.GetBitAt(bytes, 0, 0))
			println(S7.GetBitAt(bytes, 0, 1))
			println(S7.GetBitAt(bytes, 0, 2))
			println(S7.GetBitAt(bytes, 0, 3))
			println(S7.GetBitAt(bytes, 0, 4))
			println(S7.GetBitAt(bytes, 0, 5))
			println(S7.GetBitAt(bytes, 0, 6))
			println(S7.GetBitAt(bytes, 0, 7))

			S7.SetBitAt(bytes, 0, 0, true)

			s7Client.WriteArea(S7.S7AreaDB, 601, 0, 1, bytes)
		}

		println("-------------------------------------")

		s7Client.Disconnect()
	}

}
