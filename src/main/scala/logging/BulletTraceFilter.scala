package logging

import ch.qos.logback.classic.turbo.TurboFilter
import ch.qos.logback.classic.{Level, Logger}
import ch.qos.logback.core.spi.FilterReply
import org.slf4j.Marker

object BulletTraceFilter {
	private val thresholdLevels = new InheritableThreadLocal[Int]() {
		override def initialValue(): Int = Level.OFF_INT
	}

	def setLevel(l: org.slf4j.event.Level) = {
		// convert slf level to logback level
		val logbackLevel = Level.fromLocationAwareLoggerInteger(l.toInt)
		thresholdLevels.set(logbackLevel.levelInt)
	}

	def disable(): Unit = {
		thresholdLevels.set(Level.OFF_INT)
	}
}

class BulletTraceFilter extends TurboFilter {
	import BulletTraceFilter._

	override def decide(marker: Marker, logger: Logger, level: Level, format: String, params: Array[AnyRef], t: Throwable): FilterReply = {
		if(level.levelInt >= thresholdLevels.get()) {
			FilterReply.ACCEPT
		} else {
			FilterReply.NEUTRAL
		}
	}

}
