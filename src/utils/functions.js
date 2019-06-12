import { MAX_SIZE_UPLOAD } from 'src/constants/config'

export const varToEmpty = item => {
  if (item === null || item === undefined) {
    return ''
  } else {
    return item
  }
}

export const validateUploadImage = file => {
  let msg = {
    value: true,
    msg: ''
  }
  if (file.size > MAX_SIZE_UPLOAD.bytes ) {
    msg.msg = `El peso máximo permitido es ${MAX_SIZE_UPLOAD}`
    msg.value = false
  }
  return msg
}