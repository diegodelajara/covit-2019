

  export const NEW_ENTRY = newEntry => {
    let entry = {
      title : '',
      entryType : '',
      description : '',
      paymentNumber : '',
      receiptNumber : '',
      payerName : '',
      concept : '',
      wayToPay : '',
      paymentDate : '',
      amount : '',
      gloss : '',
      comments : '',
      respRegister : '',
      registrationDate : '',
      status : '',
      refDocument : '',
      // department : ''
    }
    entry.title =  newEntry.name,
    entry.entryType = newEntry.entryType,
    entry.description = "Description",
    entry.paymentNumber = "paymentNumber",
    entry.receiptNumber = "receiptNumber",
    entry.payerName = "payerName",
    entry.concept = newEntry.concept,
    entry.wayToPay = newEntry.wayToPay,
    entry.paymentDate = newEntry.date,
    entry.amount = newEntry.amount,
    entry.gloss = "gloss",
    entry.comments = newEntry.comments,
    entry.respRegister = "respRegister",
    entry.registrationDate = new Date(),
    entry.status = "ACTIVO",
    entry.refDocument = "refDocument"
    // entry.department = newEntry.department

    return entry
}
