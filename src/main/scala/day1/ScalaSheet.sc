def printHello(seq: Seq[Int]): Unit = {
	lazy val output = {
		println("About to transform sequence of integers. ")
		"Hello " + seq.map(_ * 2)
	}
	println("About to print output. ")
	println(output)
}

val msg: String = "World"

//printHello(msg)

val seq: Seq[Int] = Seq(1, 2, 3, 4, 5)

printHello(seq)