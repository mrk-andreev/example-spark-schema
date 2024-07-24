import org.apache.spark.SparkException
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{BinaryType, LongType, StringType, StructType}
import org.scalatest.funsuite.AnyFunSuite


class ErrorMsgSuite extends AnyFunSuite with SharedSparkContext {
  test("shouldThrowSchemaError") {
    val seq: Seq[Row] = Seq(
      Row(
        toBytes("0"),
        toBytes(""),
        1L,
      ),
      Row(
        toBytes("0"),
        toBytes(""),
        1L,
      ),
    )

    val schema: StructType = new StructType()
      .add("f1", BinaryType)
      .add("f3", StringType)
      .add("f2", LongType)

    val df = sqlContext.createDataFrame(sqlContext.sparkContext.parallelize(seq), schema)

    val exception = intercept[RuntimeException] {
      df.show()
    }

    assert(
      exception.getCause.getMessage
        .contains("[B is not a valid external type for schema of string")
    )
    assertResult(
      "[B is not a valid external type for schema of string at getexternalrowfield(assertnotnull(input[0, org.apache.spark.sql.Row, true]), 1, f3)"
    )(exception.getCause.getMessage)
  }



  def toBytes(x: String): Array[Byte] = x.toCharArray.map(_.toByte)
}
