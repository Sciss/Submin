def mix(a0: Seq[Int], b0: Seq[Int]): Seq[Int] = {
  val Seq(ar, ag, ab) = a0
  val Seq(br, bg, bb, ba) = b0
  val wb = ba / 255.0
  val wa = 1.0 - wb
  val r = (ar * wa + br * wb + 0.5).toInt
  val g = (ag * wa + bg * wb + 0.5).toInt
  val b = (ab * wa + bb * wb + 0.5).toInt
  Seq(r,g,b)
}

val focus = Seq(48,77,130,96)

mix(Seq(64,64,64), focus).mkString(",")
mix(Seq(48,48,48), focus).mkString(",")
