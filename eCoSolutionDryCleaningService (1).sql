USE [master]
GO
CREATE DATABASE [eCoSolutionDryCleaningService]
GO
USE [eCoSolutionDryCleaningService]
GO
CREATE TABLE [dbo].[tblAccount](
	[fldAccountID] [int] IDENTITY(1,1) NOT NULL,
	[fldUsername] [varchar](255) NOT NULL,
	[fldPassword] [varchar](255) NOT NULL,
 CONSTRAINT [PK_tblAccount_fldAccountID] PRIMARY KEY CLUSTERED ([fldAccountID] ASC))
GO
CREATE TABLE [dbo].[tblAddress](
	[fldAddressID] [int] IDENTITY(1,1) NOT NULL,
	[fldCityID] [int] NOT NULL,
	[fldStreetName] [varchar](255) NOT NULL,
	[fldStreetNum] [int] NOT NULL,
 CONSTRAINT [PK_tblAddress_fldAddressID] PRIMARY KEY CLUSTERED ([fldAddressID] ASC))
GO
CREATE TABLE [dbo].[tblCity](
	[fldCityID] [int] IDENTITY(1,1) NOT NULL,
	[fldCityName] [varchar](255) NOT NULL,
	[fldZipCode] [int] NOT NULL,
 CONSTRAINT [PK_tblCity_fldCityID] PRIMARY KEY CLUSTERED ([fldCityID] ASC))
GO
CREATE TABLE [dbo].[tblCustomer](
	[fldCustomerID] [int] IDENTITY(1,1) NOT NULL,
	[fldName] [varchar](255) NOT NULL,
	[fldSurname] [varchar](255) NOT NULL,
	[fldPhone] [varchar](255) NOT NULL,
 CONSTRAINT [PK_tblCustomer_fldCustomerID] PRIMARY KEY CLUSTERED ([fldCustomerID] ASC))
GO
CREATE TABLE [dbo].[tblDeliveryPoint](
	[fldDeliveryPointID] [int] IDENTITY(1,1) NOT NULL,
	[fldAddressID] [int] NOT NULL,
	[fldAccountID] [int] NOT NULL,
	[fldDPointName] [varchar](255) NOT NULL,
 CONSTRAINT [PK_tblDeliveryPoint_fldDeliveryPointID] PRIMARY KEY CLUSTERED ([fldDeliveryPointID] ASC))
GO
CREATE TABLE [dbo].[tblEmployee](
	[fldEmployeeID] [int] IDENTITY(1,1) NOT NULL,
	[fldAccountID] [int] NOT NULL,
	[fldRoleID] [int] NOT NULL,
	[fldStatusID] [int] NOT NULL,
	[fldName] [varchar](255) NOT NULL,
	[fldSurname] [varchar](255) NOT NULL,
	[fldPhone_no] [varchar](255) NULL,
 CONSTRAINT [PK_tblEmployee_fldEmployeeID] PRIMARY KEY CLUSTERED ([fldEmployeeID] ASC))
GO
CREATE TABLE [dbo].[tblLaundryItem](
	[fldItemID] [int] IDENTITY(1,1) NOT NULL,
	[fldItemType] [varchar](100) NOT NULL,
	[fldPrice] [float] NOT NULL,
 CONSTRAINT [PK_tblLaundryItems_fldItemID] PRIMARY KEY CLUSTERED ([fldItemID] ASC))
GO
CREATE TABLE [dbo].[tblOrder](
	[fldOrderID] [int] IDENTITY(1,1) NOT NULL,
	[fldCustomerID] [int] NULL,
	[fldOrderStatusID] [int] NULL,
	[fldDeliveryPointID] [int] NULL,
	[fldDateofOrder] [date] NULL,
 CONSTRAINT [PK_tblOrder_fldOrderID] PRIMARY KEY CLUSTERED ([fldOrderID] ASC))
GO
CREATE TABLE [dbo].[tblOrderDescription](
	[fldOrderDesID] [int] IDENTITY(1,1) NOT NULL,
	[fldOrderID] [int] NOT NULL,
	[fldItemID] [int] NOT NULL,
	[fldQuantity] [int] NOT NULL,
 CONSTRAINT [PK_tblOrderDescription_fldOrderDesID] PRIMARY KEY CLUSTERED ([fldOrderDesID] ASC))
GO
CREATE TABLE [dbo].[tblOrderStatus](
	[fldOrderStatusID] [int] IDENTITY(1,1) NOT NULL,
	[fldOrderStatus] [varchar](255) NOT NULL,
 CONSTRAINT [PK_tblOrderStatus_fldOrderStatusID] PRIMARY KEY CLUSTERED ([fldOrderStatusID] ASC))
GO
CREATE TABLE [dbo].[tblRole](
	[fldRoleID] [int] IDENTITY(1,1) NOT NULL,
	[fldRole] [varchar](255) NOT NULL,
 CONSTRAINT [PK_tblRole_fldRoleID] PRIMARY KEY CLUSTERED ([fldRoleID] ASC))
GO
CREATE TABLE [dbo].[tblStatus](
	[fldStatusID] [int] IDENTITY(1,1) NOT NULL,
	[fldStatus] [varchar](255) NOT NULL,
 CONSTRAINT [PK_tblStatus_fldStatusID] PRIMARY KEY CLUSTERED ([fldStatusID] ASC))
GO
CREATE TABLE [dbo].[tblTransaction](
	[fldTranscationID] [int] NOT NULL,
	[fldCustomerID] [int] NOT NULL,
	[fldPaymentDate] [date] NOT NULL,
	[fldAmount] [numeric](19, 0) NOT NULL,
 CONSTRAINT [PK_tblTransaction_fldTransactionID] PRIMARY KEY CLUSTERED ([fldTranscationID] ASC))
GO
SET IDENTITY_INSERT [dbo].[tblAccount] ON 
INSERT [dbo].[tblAccount] ([fldAccountID], [fldUsername], [fldPassword]) VALUES (1, N'D1001', N'1234')
INSERT [dbo].[tblAccount] ([fldAccountID], [fldUsername], [fldPassword]) VALUES (2, N'D1002', N'1235')
INSERT [dbo].[tblAccount] ([fldAccountID], [fldUsername], [fldPassword]) VALUES (3, N'D1003', N'1236')
INSERT [dbo].[tblAccount] ([fldAccountID], [fldUsername], [fldPassword]) VALUES (4, N'D1004', N'1237')
INSERT [dbo].[tblAccount] ([fldAccountID], [fldUsername], [fldPassword]) VALUES (5, N'S2001', N'1324')
INSERT [dbo].[tblAccount] ([fldAccountID], [fldUsername], [fldPassword]) VALUES (6, N'S2002', N'1233')
INSERT [dbo].[tblAccount] ([fldAccountID], [fldUsername], [fldPassword]) VALUES (7, N'S2003', N'2313')
INSERT [dbo].[tblAccount] ([fldAccountID], [fldUsername], [fldPassword]) VALUES (8, N'S2004', N'3432')
INSERT [dbo].[tblAccount] ([fldAccountID], [fldUsername], [fldPassword]) VALUES (9, N'W3001', N'2324')
INSERT [dbo].[tblAccount] ([fldAccountID], [fldUsername], [fldPassword]) VALUES (10, N'W3002', N'9593')
INSERT [dbo].[tblAccount] ([fldAccountID], [fldUsername], [fldPassword]) VALUES (11, N'M4001', N'4435')
SET IDENTITY_INSERT [dbo].[tblAccount] OFF
SET IDENTITY_INSERT [dbo].[tblAddress] ON 
INSERT [dbo].[tblAddress] ([fldAddressID], [fldCityID], [fldStreetName], [fldStreetNum]) VALUES (1, 1, N'Ullerup', 45)
INSERT [dbo].[tblAddress] ([fldAddressID], [fldCityID], [fldStreetName], [fldStreetNum]) VALUES (2, 2, N'Avnbøl', 65)
INSERT [dbo].[tblAddress] ([fldAddressID], [fldCityID], [fldStreetName], [fldStreetNum]) VALUES (3, 1, N'Aboulevard', 21)
SET IDENTITY_INSERT [dbo].[tblAddress] OFF
SET IDENTITY_INSERT [dbo].[tblCity] ON 
INSERT [dbo].[tblCity] ([fldCityID], [fldCityName], [fldZipCode]) VALUES (1, N'Sonderborg', 6400)
INSERT [dbo].[tblCity] ([fldCityID], [fldCityName], [fldZipCode]) VALUES (2, N'Copenhagen', 1050)
SET IDENTITY_INSERT [dbo].[tblCity] OFF
SET IDENTITY_INSERT [dbo].[tblCustomer] ON 
INSERT [dbo].[tblCustomer] ([fldCustomerID], [fldName], [fldSurname], [fldPhone]) VALUES (1, N'Andrina', N'Madge', N'+456786854')
INSERT [dbo].[tblCustomer] ([fldCustomerID], [fldName], [fldSurname], [fldPhone]) VALUES (2, N'Jayma', N'Kristal', N'+459854321')
INSERT [dbo].[tblCustomer] ([fldCustomerID], [fldName], [fldSurname], [fldPhone]) VALUES (3, N'Zaiden', N'Chris', N'+454332456')
INSERT [dbo].[tblCustomer] ([fldCustomerID], [fldName], [fldSurname], [fldPhone]) VALUES (4, N'Caroline', N'Forest', N'+454385295')
INSERT [dbo].[tblCustomer] ([fldCustomerID], [fldName], [fldSurname], [fldPhone]) VALUES (5, N'Paulie', N'Keara', N'+45954958493')
INSERT [dbo].[tblCustomer] ([fldCustomerID], [fldName], [fldSurname], [fldPhone]) VALUES (6, N'Tony', N'Fulk', N'+45687543687')
INSERT [dbo].[tblCustomer] ([fldCustomerID], [fldName], [fldSurname], [fldPhone]) VALUES (7, N'Hans', N'Detroit', N'+45687543689')
SET IDENTITY_INSERT [dbo].[tblCustomer] OFF
SET IDENTITY_INSERT [dbo].[tblDeliveryPoint] ON 

INSERT [dbo].[tblDeliveryPoint] ([fldDeliveryPointID], [fldAddressID], [fldAccountID], [fldDPointName]) VALUES (1, 1, 8, N'Grocery Shop')
INSERT [dbo].[tblDeliveryPoint] ([fldDeliveryPointID], [fldAddressID], [fldAccountID], [fldDPointName]) VALUES (2, 2, 9, N'Netto')
INSERT [dbo].[tblDeliveryPoint] ([fldDeliveryPointID], [fldAddressID], [fldAccountID], [fldDPointName]) VALUES (3, 3, 10, N'Bank')
SET IDENTITY_INSERT [dbo].[tblDeliveryPoint] OFF
SET IDENTITY_INSERT [dbo].[tblEmployee] ON 
INSERT [dbo].[tblEmployee] ([fldEmployeeID], [fldAccountID], [fldRoleID], [fldStatusID], [fldName], [fldSurname], [fldPhone_no]) VALUES (1, 1, 1, 1, N'Jacob', N'Smith', N'+4510856635')
INSERT [dbo].[tblEmployee] ([fldEmployeeID], [fldAccountID], [fldRoleID], [fldStatusID], [fldName], [fldSurname], [fldPhone_no]) VALUES (2, 2, 1, 2, N'Daley', N'Dione', N'+4528525484')
INSERT [dbo].[tblEmployee] ([fldEmployeeID], [fldAccountID], [fldRoleID], [fldStatusID], [fldName], [fldSurname], [fldPhone_no]) VALUES (3, 3, 1, 3, N'Avery', N'Kirsten', N'+4542723623')
INSERT [dbo].[tblEmployee] ([fldEmployeeID], [fldAccountID], [fldRoleID], [fldStatusID], [fldName], [fldSurname], [fldPhone_no]) VALUES (4, 4, 1, 1, N'Milo', N'Jerrold', N'+4510583135')
INSERT [dbo].[tblEmployee] ([fldEmployeeID], [fldAccountID], [fldRoleID], [fldStatusID], [fldName], [fldSurname], [fldPhone_no]) VALUES (5, 5, 2, 1, N'Aase', N'Lauritz', N'+4500223930')
INSERT [dbo].[tblEmployee] ([fldEmployeeID], [fldAccountID], [fldRoleID], [fldStatusID], [fldName], [fldSurname], [fldPhone_no]) VALUES (6, 6, 2, 2, N'Aynur', N'Thornton', N'+4572537996')
INSERT [dbo].[tblEmployee] ([fldEmployeeID], [fldAccountID], [fldRoleID], [fldStatusID], [fldName], [fldSurname], [fldPhone_no]) VALUES (7, 7, 3, 1, N'Adolf', N'Rani', N'+4561599062')
SET IDENTITY_INSERT [dbo].[tblEmployee] OFF
SET IDENTITY_INSERT [dbo].[tblLaundryItem] ON 

INSERT [dbo].[tblLaundryItem] ([fldItemID], [fldItemType], [fldPrice]) VALUES (1, N'Shirt', 5)
INSERT [dbo].[tblLaundryItem] ([fldItemID], [fldItemType], [fldPrice]) VALUES (2, N'Jeans', 5)
INSERT [dbo].[tblLaundryItem] ([fldItemID], [fldItemType], [fldPrice]) VALUES (3, N'Sweater', 5)
INSERT [dbo].[tblLaundryItem] ([fldItemID], [fldItemType], [fldPrice]) VALUES (4, N'Carpet', 5)
INSERT [dbo].[tblLaundryItem] ([fldItemID], [fldItemType], [fldPrice]) VALUES (5, N'Jacket', 5)
INSERT [dbo].[tblLaundryItem] ([fldItemID], [fldItemType], [fldPrice]) VALUES (6, N'Fur Jacket', 5)
INSERT [dbo].[tblLaundryItem] ([fldItemID], [fldItemType], [fldPrice]) VALUES (7, N'Socks', 5)
INSERT [dbo].[tblLaundryItem] ([fldItemID], [fldItemType], [fldPrice]) VALUES (8, N'Trousers', 5)
INSERT [dbo].[tblLaundryItem] ([fldItemID], [fldItemType], [fldPrice]) VALUES (9, N'Underpants', 5)
SET IDENTITY_INSERT [dbo].[tblLaundryItem] OFF
SET IDENTITY_INSERT [dbo].[tblOrder] ON 

INSERT [dbo].[tblOrder] ([fldOrderID], [fldCustomerID], [fldOrderStatusID], [fldDeliveryPointID], [fldDateofOrder]) VALUES (1, 1, 1, 1, CAST(N'2020-04-20' AS Date))
INSERT [dbo].[tblOrder] ([fldOrderID], [fldCustomerID], [fldOrderStatusID], [fldDeliveryPointID], [fldDateofOrder]) VALUES (2, 2, 2, 1, CAST(N'2020-04-20' AS Date))
INSERT [dbo].[tblOrder] ([fldOrderID], [fldCustomerID], [fldOrderStatusID], [fldDeliveryPointID], [fldDateofOrder]) VALUES (3, 2, 3, 3, CAST(N'2020-04-24' AS Date))
INSERT [dbo].[tblOrder] ([fldOrderID], [fldCustomerID], [fldOrderStatusID], [fldDeliveryPointID], [fldDateofOrder]) VALUES (4, 3, 4, 1, CAST(N'2020-04-22' AS Date))
INSERT [dbo].[tblOrder] ([fldOrderID], [fldCustomerID], [fldOrderStatusID], [fldDeliveryPointID], [fldDateofOrder]) VALUES (5, 4, 5, 2, CAST(N'2020-04-21' AS Date))
INSERT [dbo].[tblOrder] ([fldOrderID], [fldCustomerID], [fldOrderStatusID], [fldDeliveryPointID], [fldDateofOrder]) VALUES (6, 5, 6, 2, CAST(N'2020-04-23' AS Date))
INSERT [dbo].[tblOrder] ([fldOrderID], [fldCustomerID], [fldOrderStatusID], [fldDeliveryPointID], [fldDateofOrder]) VALUES (7, 5, 6, 2, CAST(N'2020-04-23' AS Date))
SET IDENTITY_INSERT [dbo].[tblOrder] OFF
SET IDENTITY_INSERT [dbo].[tblOrderDescription] ON 

INSERT [dbo].[tblOrderDescription] ([fldOrderDesID], [fldOrderID], [fldItemID], [fldQuantity]) VALUES (1, 1, 2, 10)
INSERT [dbo].[tblOrderDescription] ([fldOrderDesID], [fldOrderID], [fldItemID], [fldQuantity]) VALUES (2, 2, 2, 10)
INSERT [dbo].[tblOrderDescription] ([fldOrderDesID], [fldOrderID], [fldItemID], [fldQuantity]) VALUES (3, 3, 3, 5)
INSERT [dbo].[tblOrderDescription] ([fldOrderDesID], [fldOrderID], [fldItemID], [fldQuantity]) VALUES (4, 4, 2, 1)
INSERT [dbo].[tblOrderDescription] ([fldOrderDesID], [fldOrderID], [fldItemID], [fldQuantity]) VALUES (5, 5, 4, 5)
INSERT [dbo].[tblOrderDescription] ([fldOrderDesID], [fldOrderID], [fldItemID], [fldQuantity]) VALUES (6, 6, 4, 2)
SET IDENTITY_INSERT [dbo].[tblOrderDescription] OFF
SET IDENTITY_INSERT [dbo].[tblOrderStatus] ON 

INSERT [dbo].[tblOrderStatus] ([fldOrderStatusID], [fldOrderStatus]) VALUES (1, N'Confirmed')
INSERT [dbo].[tblOrderStatus] ([fldOrderStatusID], [fldOrderStatus]) VALUES (2, N'Under_Way')
INSERT [dbo].[tblOrderStatus] ([fldOrderStatusID], [fldOrderStatus]) VALUES (3, N'In Transit')
INSERT [dbo].[tblOrderStatus] ([fldOrderStatusID], [fldOrderStatus]) VALUES (4, N'Cleaning')
INSERT [dbo].[tblOrderStatus] ([fldOrderStatusID], [fldOrderStatus]) VALUES (5, N'Delivered')
INSERT [dbo].[tblOrderStatus] ([fldOrderStatusID], [fldOrderStatus]) VALUES (6, N'Complete')
SET IDENTITY_INSERT [dbo].[tblOrderStatus] OFF
SET IDENTITY_INSERT [dbo].[tblRole] ON 
INSERT [dbo].[tblRole] ([fldRoleID], [fldRole]) VALUES (1, N'Driver')
INSERT [dbo].[tblRole] ([fldRoleID], [fldRole]) VALUES (2, N'Laundry Worker')
INSERT [dbo].[tblRole] ([fldRoleID], [fldRole]) VALUES (3, N'Manager')
SET IDENTITY_INSERT [dbo].[tblRole] OFF
SET IDENTITY_INSERT [dbo].[tblStatus] ON 

INSERT [dbo].[tblStatus] ([fldStatusID], [fldStatus]) VALUES (1, N'Signed in')
INSERT [dbo].[tblStatus] ([fldStatusID], [fldStatus]) VALUES (2, N'Signed off')
INSERT [dbo].[tblStatus] ([fldStatusID], [fldStatus]) VALUES (3, N'Vacation')
SET IDENTITY_INSERT [dbo].[tblStatus] OFF

CREATE NONCLUSTERED INDEX [FK] ON [dbo].[tblAddress]
([fldCityID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [FK]    Script Date: 05-06-2020 10:25:15 ******/
CREATE NONCLUSTERED INDEX [FK] ON [dbo].[tblDeliveryPoint]
(
	[fldAddressID] ASC,
	[fldAccountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [FK]    Script Date: 05-06-2020 10:25:15 ******/
CREATE NONCLUSTERED INDEX [FK] ON [dbo].[tblEmployee]
(
	[fldAccountID] ASC,
	[fldRoleID] ASC,
	[fldStatusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [FK]    Script Date: 05-06-2020 10:25:15 ******/
CREATE NONCLUSTERED INDEX [FK] ON [dbo].[tblOrder]
(
	[fldCustomerID] ASC,
	[fldOrderStatusID] ASC,
	[fldDeliveryPointID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [FK]    Script Date: 05-06-2020 10:25:15 ******/
CREATE NONCLUSTERED INDEX [FK] ON [dbo].[tblOrderDescription]
(
	[fldOrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[tblAddress]  WITH CHECK ADD  CONSTRAINT [FK_tblAddress_tblCity] FOREIGN KEY([fldCityID])
REFERENCES [dbo].[tblCity] ([fldCityID])
GO
ALTER TABLE [dbo].[tblAddress] CHECK CONSTRAINT [FK_tblAddress_tblCity]
GO
ALTER TABLE [dbo].[tblDeliveryPoint]  WITH CHECK ADD  CONSTRAINT [FK_tblDeliveryPoint_tblAccount] FOREIGN KEY([fldAccountID])
REFERENCES [dbo].[tblAccount] ([fldAccountID])
GO
ALTER TABLE [dbo].[tblDeliveryPoint] CHECK CONSTRAINT [FK_tblDeliveryPoint_tblAccount]
GO
ALTER TABLE [dbo].[tblDeliveryPoint]  WITH CHECK ADD  CONSTRAINT [FK_tblDeliveryPoint_tblAddress] FOREIGN KEY([fldAddressID])
REFERENCES [dbo].[tblAddress] ([fldAddressID])
GO
ALTER TABLE [dbo].[tblDeliveryPoint] CHECK CONSTRAINT [FK_tblDeliveryPoint_tblAddress]
GO
ALTER TABLE [dbo].[tblEmployee]  WITH CHECK ADD  CONSTRAINT [FK_tblEmployee_tblAccount] FOREIGN KEY([fldAccountID])
REFERENCES [dbo].[tblAccount] ([fldAccountID])
GO
ALTER TABLE [dbo].[tblEmployee] CHECK CONSTRAINT [FK_tblEmployee_tblAccount]
GO
ALTER TABLE [dbo].[tblEmployee]  WITH CHECK ADD  CONSTRAINT [FK_tblEmployee_tblRole] FOREIGN KEY([fldRoleID])
REFERENCES [dbo].[tblRole] ([fldRoleID])
GO
ALTER TABLE [dbo].[tblEmployee] CHECK CONSTRAINT [FK_tblEmployee_tblRole]
GO
ALTER TABLE [dbo].[tblEmployee]  WITH CHECK ADD  CONSTRAINT [FK_tblEmployee_tblStatus] FOREIGN KEY([fldStatusID])
REFERENCES [dbo].[tblStatus] ([fldStatusID])
GO
ALTER TABLE [dbo].[tblEmployee] CHECK CONSTRAINT [FK_tblEmployee_tblStatus]
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_tblCustomer] FOREIGN KEY([fldCustomerID])
REFERENCES [dbo].[tblCustomer] ([fldCustomerID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_tblOrder_tblCustomer]
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_tblDeliveryPoint] FOREIGN KEY([fldDeliveryPointID])
REFERENCES [dbo].[tblDeliveryPoint] ([fldDeliveryPointID])
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_tblOrder_tblDeliveryPoint]
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_tblOrderStatus] FOREIGN KEY([fldOrderStatusID])
REFERENCES [dbo].[tblOrderStatus] ([fldOrderStatusID])
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_tblOrder_tblOrderStatus]
GO
ALTER TABLE [dbo].[tblOrderDescription]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDescription_tblLaundryItem] FOREIGN KEY([fldItemID])
REFERENCES [dbo].[tblLaundryItem] ([fldItemID])
GO
ALTER TABLE [dbo].[tblOrderDescription] CHECK CONSTRAINT [FK_tblOrderDescription_tblLaundryItem]
GO
ALTER TABLE [dbo].[tblOrderDescription]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDescription_tblOrder] FOREIGN KEY([fldOrderID])
REFERENCES [dbo].[tblOrder] ([fldOrderID])
GO
ALTER TABLE [dbo].[tblOrderDescription] CHECK CONSTRAINT [FK_tblOrderDescription_tblOrder]
GO
ALTER TABLE [dbo].[tblTransaction]  WITH CHECK ADD  CONSTRAINT [FK_tblTransaction_tblCustomer] FOREIGN KEY([fldCustomerID])
REFERENCES [dbo].[tblCustomer] ([fldCustomerID])
GO
ALTER TABLE [dbo].[tblTransaction] CHECK CONSTRAINT [FK_tblTransaction_tblCustomer]
GO

/****** Object:  StoredProcedure [dbo].[checkorder]    Script Date: 05-06-2020 10:25:15 ******/
CREATE PROCEDURE [dbo].[checkorder](@orderID int)
AS
	BEGIN
		SELECT 
			LI.fldItemType, OD.fldQuantity
		FROM
			tblLaundryItem AS LI
		INNER JOIN
			tblOrderDescription AS OD ON OD.fldItemID = LI.fldItemID
		WHERE
			OD.fldOrderID = @orderID
	END
GO
/****** Object:  StoredProcedure [dbo].[fetchemployeeData]    Script Date: 05-06-2020 10:25:15 ******/
CREATE PROCEDURE [dbo].[fetchemployeeData]
	AS
		BEGIN
			SELECT
				tblEmployee.fldEmployeeID,tblEmployee.fldName,tblEmployee.fldSurname
				,tblEmployee.fldPhone_no, tblStatus.fldStatus, tblRole.fldRole
			FROM
				tblRole
			INNER JOIN
			(tblEmployee INNER JOIN tblStatus
			ON tblStatus.fldStatusID = tblEmployee.fldStatusID)
			ON tblRole.fldRoleID = tblEmployee.fldRoleID;
		END
GO
/****** Object:  StoredProcedure [dbo].[getorderstatus]    Script Date: 05-06-2020 10:25:15 ******/
CREATE PROCEDURE [dbo].[getorderstatus]
AS
BEGIN
	SELECT 
		tblOrder.fldOrderID, tblOrderStatus.fldOrderStatus
	FROM 
		tblOrder
	INNER JOIN 
		tblOrderStatus ON tblOrderStatus.fldOrderStatusID = tblOrder.fldOrderStatusID
	WHERE 
		tblOrderStatus.fldOrderStatusID = 4 OR tblOrderStatus.fldOrderStatusID = 3
END
GO
/****** Object:  StoredProcedure [dbo].[orderdataSelect]    Script Date: 05-06-2020 10:25:15 ******/
CREATE PROCEDURE [dbo].[orderdataSelect]
AS
	BEGIN
		SELECT 
			tblOrder.fldOrderID, tblOrderStatus.fldOrderStatus, tblDeliveryPoint.fldDPointName, tblCustomer.fldName, tblCustomer.fldSurname
			,tblOrder.fldDateofOrder
		FROM
			tblDeliveryPoint
		INNER JOIN
			(tblOrder INNER JOIN tblOrderStatus ON tblOrderStatus.fldOrderStatusID = tblOrder.fldOrderStatusID
			INNER JOIN tblCustomer ON tblCustomer.fldCustomerID = tblOrder.fldCustomerID
			INNER JOIN tblOrderDescription ON tblOrderDescription.fldOrderID = tblOrder.fldOrderID)
		ON
			tblDeliveryPoint.fldDeliveryPointID = tblOrder.fldDeliveryPointID
	END

GO
/****** Object:  StoredProcedure [dbo].[orderstatus_forDriver]    Script Date: 05-06-2020 10:25:15 ******/
CREATE PROCEDURE [dbo].[orderstatus_forDriver]
AS
BEGIN
	SELECT
		tblOrder.fldOrderID, tblOrderStatus.fldOrderStatus, tblDeliveryPoint.fldDPointName
	FROM 
		tblDeliveryPoint
	INNER JOIN 
		(tblOrder INNER JOIN tblOrderStatus ON tblOrderStatus.fldOrderStatusID = tblOrder.fldOrderStatusID)
	ON
		 tblDeliveryPoint.fldDeliveryPointID = tblOrder.fldDeliveryPointID
	WHERE 
		tblOrder.fldOrderStatusID != 4 AND tblOrder.fldDeliveryPointID IS NOT NULL
END
GO
/****** Object:  StoredProcedure [dbo].[update_orderstatus]    Script Date: 05-06-2020 10:25:15 ******/
CREATE PROCEDURE [dbo].[update_orderstatus](@status AS varchar(50), @orderID AS int)
AS
BEGIN
	UPDATE
		tblOrder
	SET
		tblOrder.fldOrderStatusID = (SELECT fldOrderStatusID FROM tblOrderStatus WHERE fldOrderStatus = @status)
	FROM
		tblOrder
	INNER JOIN
		tblOrderStatus
	ON
		tblOrder.fldOrderStatusID = tblOrderStatus.fldOrderStatusID
	WHERE 
		tblOrder.fldOrderID = @orderID;
END

GO
CREATE PROCEDURE [done_SMS](@getOrderID AS int)
AS 
BEGIN 
	SELECT 
		tblOrder.fldOrderID, tblOrder.fldCustomerID, tblCustomer.fldPhone, tblCustomer.fldName, tblCustomer.fldSurname
		FROM 
		tblOrder
		INNER JOIN
		tblCustomer ON tblOrder.fldCustomerID = tblCustomer.fldCustomerID
		 where(fldOrderID = @getOrderID)
END 
GO
CREATE PROCEDURE insertnewOrder(@customerid int, @orderstatusid int, @dpid int, @date date)
AS
    BEGIN
        INSERT 
            tblOrder(fldCustomerID,fldOrderStatusID,fldDeliveryPointID,fldDateofOrder)
        VALUES
            (@customerid,@orderstatusid,@dpid,@date)

    END
GO
USE [master]
GO
ALTER DATABASE [eCoSolutionDryCleaningService] SET  READ_WRITE 
GO
