(BLOCK
  (INPUT size)
  (SET i 0)
  (WHILE (LT i size)
    (BLOCK
      (IF (GT i 3)
        (PRINT i)
        (PRINT 0)
      )
      (SET i (ADD i 1))
    )
  )
)