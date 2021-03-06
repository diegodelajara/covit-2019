import { MAX_SIZE_UPLOAD, TYPES } from 'src/constants/config'
import { BACK_ERRORS } from "src/constants/errors"

import {
  IMAGE_SIZE_TITLE_FAILURE,
  IMAGE_SIZE_TEXT_FAILURE,
  IMAGE_TYPE_TITLE_FAILURE,
  IMAGE_TYPE_TEXT_FAILURE
} from 'src/constants/alerts'

export const varToEmpty = item => {
  if (item === null || item === undefined) {
    return ''
  } else {
    return item
  }
}

export const validateUploadImage = file => {
  let msg = {
    title: '',
    text: '',
    value: true
  }

  if (!TYPES.includes(file.type)) {
    msg.title = IMAGE_TYPE_TITLE_FAILURE
    msg.text = IMAGE_TYPE_TEXT_FAILURE
    msg.value = false
  }
  else if (file.size > MAX_SIZE_UPLOAD.bytes ) {
    msg.title = IMAGE_SIZE_TITLE_FAILURE
    msg.text = IMAGE_SIZE_TEXT_FAILURE
    msg.value = false
  }
  return msg
}

export const getErrorMessage = status => {
  // console.log('%c code', 'color: tomato;', code)
  return BACK_ERRORS.find(item => item.status == status)
}
