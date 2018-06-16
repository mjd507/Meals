const Utils = {
  /**
   * 将时间戳转换为 yyyy-MM-dd HH:mm:ss
   * @param {LongRange} dateTime 时间戳
   */
  formatDateTime(dateTime) {
    const date = new Date(dateTime)
    const year = date.getFullYear().toString();
    const month = `${date.getMonth() + 1}`;
    const showMonth = month.length > 1 ? month : `0${month}`
    const day = date.getUTCDate().toString();
    const showDay = day.length > 1 ? day : `0${day}`
    const hour = date.getHours().toString()
    const showHour = hour.length > 1 ? hour : `0${hour}`
    const min = date.getMinutes().toString()
    const showMin = min.length > 1 ? min : `0${min}`
    const sec = date.getSeconds().toString()
    const showSec = sec.length > 1 ? sec : `0${sec}`
    return `${year}-${showMonth}-${showDay} ${showHour}:${showMin}:${showSec}`
  },
  /**
   * 将时间戳转换为 HH:mm:ss
   * @param {LongRange} dateTime 时间戳
   */
  formatDateTime2(dateTime) {
    const date = new Date(dateTime)
    const hour = date.getHours().toString()
    const showHour = hour.length > 1 ? hour : `0${hour}`
    const min = date.getMinutes().toString()
    const showMin = min.length > 1 ? min : `0${min}`
    const sec = date.getSeconds().toString()
    const showSec = sec.length > 1 ? sec : `0${sec}`
    return `${showHour}:${showMin}:${showSec}`
  }
}

export default Utils
