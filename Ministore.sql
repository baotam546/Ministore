DROP DATABASE [MinistoreManagement]
USE [master]
GO
/****** Object:  Database [MinistoreManagement]    Script Date: 15/05/2023 07:02:12 ******/
CREATE DATABASE [MinistoreManagement]

USE [MinistoreManagement]
/****** Object:  Table [dbo].[order]    Script Date: 15/05/2023 07:02:12 ******/

CREATE TABLE [dbo].[order](
	[orderId] [int] IDENTITY(1,1) NOT NULL,
	[type] [bit] NOT NULL,
	[userId] [int] NOT NULL,
	[date] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[orderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orderDetails]    Script Date: 15/05/2023 07:02:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orderDetails](
	[orderDetailsId] [int] IDENTITY(1,1) NOT NULL,
	[productId] [int] NOT NULL,
	[orderId] [int] NOT NULL,
	[quantity] [int]  NULL,
	[price] [decimal](10, 2)  NULL,
	[total] [decimal](10, 2)  NULL,
	[productVoucherId] [int]  NULL,
 CONSTRAINT [PK__orderDet__3213E83FEAB6E822] PRIMARY KEY CLUSTERED 
(
	[orderDetailsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[payslip]    Script Date: 15/05/2023 07:02:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[payslip](
	[payslipId] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NOT NULL,
	[startDate] [date] NULL,
	[endDate] [date] NULL,
	[shiftCount] int NULL,
	[salary] [decimal](10, 2) NULL
PRIMARY KEY CLUSTERED 
(
	[payslipId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product]    Script Date: 15/05/2023 07:02:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product](
	[productId] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255)  NULL,
	[quantity] [int]  NULL,
	[productTypeId] [int] NOT NULL,
	[price] [decimal](10, 2)  NULL,
PRIMARY KEY CLUSTERED 
(
	[productId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[productImg]    Script Date: 15/05/2023 07:02:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[productImg](
	[productImgId] [int] IDENTITY(1,1) NOT NULL,
	[productId] [int] NOT NULL,
	[imgUrl] [text]  NULL,
PRIMARY KEY CLUSTERED 
(
	[productImgId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[productType]    Script Date: 15/05/2023 07:02:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[productType](
	[productTypeId] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255)  NULL,
PRIMARY KEY CLUSTERED 
(
	[productTypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[productVoucher]    Script Date: 15/05/2023 07:02:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[productVoucher](
	[productVoucherId] [int] IDENTITY(1,1) NOT NULL,
	[voucherId] [int] NOT NULL,
	[productId] [int] NOT NULL,
	
PRIMARY KEY CLUSTERED 
(
	[productVoucherId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[role]    Script Date: 15/05/2023 07:02:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role](
	[roleId] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255)  NULL,
PRIMARY KEY CLUSTERED 
(
	[roleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[shift]    Script Date: 15/05/2023 07:02:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[shift](
	[shiftId] [int] IDENTITY(1,1) NOT NULL,
	[startWorkHour] [decimal](5, 3)  NULL,
	[endWorkHour] [decimal](5, 3)  NULL,
	[coefficient] [int]  NULL,
PRIMARY KEY CLUSTERED 
(
	[shiftId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user]    Script Date: 15/05/2023 07:02:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user](
	[userId] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255)  NULL,
	[email] [varchar](255)  NULL,
	[password] [varchar](255)  NULL,
	[phone] [varchar](255) NULL,
	[address] [varchar](255)  NULL,
	[roleId] [int] NOT NULL,
	
PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[userShift]    Script Date: 15/05/2023 07:02:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[userShift](
	[userShiftId] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NOT NULL,
	[shiftId] [int] NOT NULL,
	[workDate] [date]  NULL,
	[isHoliday] [bit]  NULL,
	[isWeekend] [bit]  NULL,
	[isPresent] [bit]  NULL,
 CONSTRAINT [PK__schedule__3213E83F6C24A4B0] PRIMARY KEY CLUSTERED 
(
	[userShiftId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[voucher]    Script Date: 15/05/2023 07:02:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[voucher](
	[voucherId] [int] IDENTITY(1,1) NOT NULL,
	[description] [nvarchar](max)  NULL,
	[quantity] [int] NULL,
	[percentDiscount] DECIMAL(5, 2) NULL
PRIMARY KEY CLUSTERED 
(
	[voucherId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD  CONSTRAINT [fk10] FOREIGN KEY([userId])
REFERENCES [dbo].[user] ([userId])
GO
ALTER TABLE [dbo].[order] CHECK CONSTRAINT [fk10]
GO
ALTER TABLE [dbo].[orderDetails]  WITH CHECK ADD  CONSTRAINT [fk2] FOREIGN KEY([productId])
REFERENCES [dbo].[product] ([productId])
GO
ALTER TABLE [dbo].[orderDetails] CHECK CONSTRAINT [fk2]
GO
ALTER TABLE [dbo].[orderDetails]  WITH CHECK ADD  CONSTRAINT [fk21] FOREIGN KEY([productVoucherId])
REFERENCES [dbo].[productVoucher] ([productVoucherId])
GO
ALTER TABLE [dbo].[orderDetails] CHECK CONSTRAINT [fk21]
GO
ALTER TABLE [dbo].[orderDetails]  WITH CHECK ADD  CONSTRAINT [fk3] FOREIGN KEY([orderId])
REFERENCES [dbo].[order] ([orderId])
GO
ALTER TABLE [dbo].[orderDetails] CHECK CONSTRAINT [fk3]
GO
ALTER TABLE [dbo].[payslip]  WITH CHECK ADD  CONSTRAINT [fk40] FOREIGN KEY([userId])
REFERENCES [dbo].[user] ([userId])
GO
ALTER TABLE [dbo].[payslip] CHECK CONSTRAINT [fk40]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [fk1] FOREIGN KEY([productTypeId])
REFERENCES [dbo].[productType] ([productTypeId])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [fk1]
GO
ALTER TABLE [dbo].[productImg]  WITH CHECK ADD  CONSTRAINT [fk12] FOREIGN KEY([productId])
REFERENCES [dbo].[product] ([productId])
GO
ALTER TABLE [dbo].[productImg] CHECK CONSTRAINT [fk12]
GO
ALTER TABLE [dbo].[productVoucher]  WITH CHECK ADD  CONSTRAINT [fk15] FOREIGN KEY([voucherId])
REFERENCES [dbo].[voucher] ([voucherId])
GO
ALTER TABLE [dbo].[productVoucher] CHECK CONSTRAINT [fk15]
GO
ALTER TABLE [dbo].[productVoucher]  WITH CHECK ADD  CONSTRAINT [fk20] FOREIGN KEY([productId])
REFERENCES [dbo].[product] ([productId])
GO
ALTER TABLE [dbo].[productVoucher] CHECK CONSTRAINT [fk20]
GO
ALTER TABLE [dbo].[user]  WITH CHECK ADD  CONSTRAINT [fk4] FOREIGN KEY([roleId])
REFERENCES [dbo].[role] ([roleId])
GO
ALTER TABLE [dbo].[user] CHECK CONSTRAINT [fk4]
GO
ALTER TABLE [dbo].[userShift]  WITH CHECK ADD  CONSTRAINT [fk5] FOREIGN KEY([userId])
REFERENCES [dbo].[user] ([userId])
GO
ALTER TABLE [dbo].[userShift] CHECK CONSTRAINT [fk5]
GO
ALTER TABLE [dbo].[userShift]  WITH CHECK ADD  CONSTRAINT [fk6] FOREIGN KEY([shiftId])
REFERENCES [dbo].[shift] ([shiftId])
GO
ALTER TABLE [dbo].[userShift] CHECK CONSTRAINT [fk6]
GO
USE [master]
GO
ALTER DATABASE [MinistoreManagement] SET  READ_WRITE 
GO
